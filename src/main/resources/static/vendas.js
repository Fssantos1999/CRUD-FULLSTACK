const apiUrl = 'http://localhost:8080/api/vendas'; // URL da sua API

document.addEventListener('DOMContentLoaded', fetchVendas);

// Função para buscar todas as vendas e exibi-las na tabela
function fetchVendas() {
    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                alert('Desculpe, não consegui conectar ao servidor. Tente novamente mais tarde.'); // Mensagem amigável
                throw new Error('Problema na conexão com a API');
            }
            return response.json();
        })
        .then(vendas => {
            const vendasTableBody = document.querySelector('#vendasTable tbody');
            vendasTableBody.innerHTML = ''; // Limpa o corpo da tabela antes de preencher

            // Verifica se existem vendas
            if (vendas.length === 0) {
                vendasTableBody.innerHTML = '<tr><td colspan="7">Nenhuma venda registrada ainda.</td></tr>';
                return;
            }

            vendas.forEach(venda => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${venda.id}</td>
                    <td>${venda.nome}</td>
                    <td>${venda.sobrenome}</td>
                    <td>${venda.cpf}</td>
                    <td>${venda.dataVenda}</td>
                    <td>R$ ${venda.valorTotal.toFixed(2)}</td>
                    <td>
                        <button onclick="editVenda(${venda.id})">Editar</button>
                        <button onclick="deleteVenda(${venda.id})">Excluir</button>
                    </td>
                `;
                vendasTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Erro:', error.message));
}

// Função para registrar uma nova venda
document.getElementById('vendaForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o envio padrão do formulário
    const venda = {
        nome: document.getElementById('nome').value,
        sobrenome: document.getElementById('sobrenome').value,
        cpf: document.getElementById('cpf').value,
        dataVenda: document.getElementById('dataVenda').value,
        valorTotal: parseFloat(document.getElementById('valorTotal').value),
    };

    fetch(apiUrl + '/registrarVenda', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(venda),
    })
        .then(response => {
            if (response.status === 409) { // CPF já cadastrado
                return response.text().then(message => alert('Erro: ' + message)); // Mensagem amigável
            }
            if (!response.ok) {
                alert('Houve um erro ao registrar a venda. Verifique os dados e tente novamente.'); // Mensagem amigável
                throw new Error('Erro ao registrar venda');
            }
            alert('Venda registrada com sucesso!'); // Mensagem amigável
            fetchVendas(); // Atualiza a tabela após o registro
            document.getElementById('vendaForm').reset(); // Limpa o formulário
        })
        .catch(error => console.error('Erro:', error.message));
});

// Função para editar uma venda
function editVenda(id) {
    fetch(`${apiUrl}/${id}`)
        .then(response => response.json())
        .then(venda => {
            document.getElementById('id').value = venda.id;
            document.getElementById('nome').value = venda.nome;
            document.getElementById('sobrenome').value = venda.sobrenome;
            document.getElementById('cpf').value = venda.cpf;
            document.getElementById('dataVenda').value = venda.dataVenda;
            document.getElementById('valorTotal').value = venda.valorTotal;
           //NECESSARIO A IMPLANTACAO DO TELEFONE DO CLIENTE document.getElementById('telefone').value = venda.telefone;
        })
        .catch(error => {
            alert('Houve um erro ao carregar os dados da venda. Tente novamente.'); // Mensagem amigável
            console.error('Erro ao buscar venda para edição:', error.message);
        });
}

// Função para atualizar a venda
function updateVenda(event) {
    event.preventDefault();
    const id = document.getElementById('id').value;
    const venda = {
        nome: document.getElementById('nome').value,
        sobrenome: document.getElementById('sobrenome').value,
        cpf: document.getElementById('cpf').value,
        dataVenda: document.getElementById('dataVenda').value,
        valorTotal: parseFloat(document.getElementById('valorTotal').value),
    };

    fetch(`${apiUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(venda),
    })
        .then(response => {
            if (!response.ok) {
                alert('Houve um erro ao atualizar a venda. Verifique os dados e tente novamente.');
                throw new Error('Erro ao atualizar venda');
            }
            alert('Venda atualizada com sucesso!');
            fetchVendas(); // Atualiza a tabela após a atualização
            document.getElementById('vendaForm').reset(); // Limpa o formulário
        })
        .catch(error => console.error('Erro:', error.message));
}

// Função para excluir uma venda
function deleteVenda(id) {
    if (confirm('Tem certeza que deseja excluir esta venda? Esta ação não pode ser desfeita.')) {
        fetch(`${apiUrl}/${id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (!response.ok) {
                    alert('Houve um erro ao tentar excluir a venda. Tente novamente.');
                    throw new Error('Erro ao excluir venda');
                }
                alert('Venda excluída com sucesso!');
                fetchVendas(); // Atualiza a tabela após a exclusão
            })
            .catch(error => console.error('Erro:', error.message));
    }
}
