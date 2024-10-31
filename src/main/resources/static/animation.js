const particlesCount = 100; // Número de partículas
const particles = [];

function createParticle() {
    return {
        x: Math.random() * window.innerWidth,
        y: Math.random() * window.innerHeight,
        radius: Math.random() * 3 + 1, // Raio aleatório
        color: 'rgba(72, 181, 90, 0.7)', // Cor verde suave
        speed: Math.random() * 2 + 0.5 // Velocidade aleatória
    };
}

// Cria as partículas
for (let i = 0; i < particlesCount; i++) {
    particles.push(createParticle());
}

function updateParticles() {
    const canvas = document.createElement('canvas');
    const ctx = canvas.getContext('2d');
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    document.querySelector('.animation-background').appendChild(canvas);

    function draw() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        particles.forEach((particle, index) => {
            particle.y -= particle.speed; // Move a partícula para cima
            if (particle.y < 0) {
                particles[index] = createParticle(); // Reinicia a partícula
            }

            ctx.beginPath();
            ctx.arc(particle.x, particle.y, particle.radius, 0, Math.PI * 2);
            ctx.fillStyle = particle.color;
            ctx.fill();
        });

        requestAnimationFrame(draw); // Atualiza a animação
    }
    draw();
}

window.onload = updateParticles;