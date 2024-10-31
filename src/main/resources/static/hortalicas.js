const API_URL = 'http://localhost:8080/api/hortalicas';
let hortalicaIdAtual = null;

// Função para listar hortaliças
async function listarHortalicas() {
    const response = await fetch(API_URL);
    const hortalicas = await response.json();
    const tabela = document.querySelector('#tabelaHortalicas tbody');
    tabela.innerHTML = '';

    hortalicas.forEach(hortalica => {
        const row = tabela.insertRow();
        row.innerHTML = `
            <td>${hortalica.id}</td>
            <td>${hortalica.nome}</td>
            <td>${hortalica.medidasProtetivas}</td>
            <td>${hortalica.tipoSoloPH}</td>
            <td>${hortalica.melhorEpoca}</td>
            <td>${hortalica.preco}</td>
            <td>
                <button class="action-button action-edit" onclick="editarHortalica(${hortalica.id})">Editar</button>
                <button class="action-button action-delete" onclick="removerHortalica(${hortalica.id})">Remover</button>
            </td>
        `;
    });
}

// Função para adicionar uma nova hortaliça
async function adicionarHortalica() {
    const novaHortalica = {
        nome: document.getElementById('nome').value,
        medidasProtetivas: document.getElementById('medidasProtetivas').value,
        tipoSoloPH: document.getElementById('tipoSoloPH').value,
        melhorEpoca: document.getElementById('melhorEpoca').value,
        preco: parseFloat(document.getElementById('preco').value)
    };

    try {
        const response = await fetch(`${API_URL}/adicionarHortalica`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novaHortalica)
        });

        if (!response.ok) {
            throw new Error('Erro ao adicionar a hortaliça');
        }

        const addedHortalica = await response.json();
        console.log('Hortaliça adicionada:', addedHortalica);
        alert('Hortaliça adicionada com sucesso!');

        // Limpa o formulário e atualiza a lista
        document.getElementById('formHortalica').reset();
        listarHortalicas();
    } catch (error) {
        console.error('Erro ao adicionar hortaliça:', error);
        alert('Ocorreu um erro ao adicionar a hortaliça.');
    }
}

// Função para editar uma hortaliça
async function editarHortalica(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        if (!response.ok) {
            throw new Error('Erro ao buscar a hortaliça');
        }
        const hortalica = await response.json();

        // Preencher o formulário com os dados da hortaliça
        if (hortalica) {
            document.getElementById('nome').value = hortalica.nome || '';
            document.getElementById('medidasProtetivas').value = hortalica.medidasProtetivas || '';
            document.getElementById('tipoSoloPH').value = hortalica.tipoSoloPH || '';
            document.getElementById('melhorEpoca').value = hortalica.melhorEpoca || '';
            document.getElementById('preco').value = hortalica.preco || '';

            hortalicaIdAtual = id; // Armazenar o ID da hortaliça que está sendo editada
        } else {
            console.error('Hortalica não encontrada');
        }
    } catch (error) {
        console.error('Erro ao editar hortaliça:', error);
    }
}

// Função para atualizar uma hortaliça
async function atualizarHortalica(event) {
    event.preventDefault(); // Previne o envio padrão do formulário

    if (hortalicaIdAtual === null) {
        alert("Nenhuma hortaliça selecionada para atualizar.");
        return;
    }

    const hortalicaAtualizada = {
        nome: document.getElementById('nome').value,
        medidasProtetivas: document.getElementById('medidasProtetivas').value,
        tipoSoloPH: document.getElementById('tipoSoloPH').value,
        melhorEpoca: document.getElementById('melhorEpoca').value,
        preco: parseFloat(document.getElementById('preco').value)
    };

    try {
        const response = await fetch(`${API_URL}/atualizarHortalica/${hortalicaIdAtual}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(hortalicaAtualizada)
        });

        if (!response.ok) {
            throw new Error('Erro ao atualizar a hortaliça');
        }

        const updatedHortalica = await response.json();
        console.log('Hortaliça atualizada:', updatedHortalica);
        alert('Hortaliça atualizada com sucesso!');

        // Limpar o formulário e o ID atual
        document.getElementById('formHortalica').reset();
        hortalicaIdAtual = null; // Reseta o ID da hortaliça
        listarHortalicas(); // Atualiza a lista de hortaliças
    } catch (error) {
        console.error('Erro ao atualizar hortaliça:', error);
        alert('Ocorreu um erro ao atualizar a hortaliça.');
    }
}

// Função para remover uma hortaliça
async function removerHortalica(id) {
    await fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
    });
    listarHortalicas();
}

// Adiciona um listener para o formulário
document.getElementById('formHortalica').addEventListener('submit', async (event) => {
    if (hortalicaIdAtual) {
        await atualizarHortalica(event); // Chama a função de atualizar se estiver editando
    } else {
        await adicionarHortalica(event); // Chama a função de adicionar se não estiver editando
    }
});

// Lista as hortaliças ao carregar a página
window.onload = listarHortalicas;
