document.addEventListener('DOMContentLoaded', function() {
    const queueContainer = document.getElementById('queueItems');
    const ingredientsMonitoring = document.getElementById('ingredientsMonitoring');
    const refreshBtn = document.getElementById('refreshQueue');
    let currentItemId = null;
    const statusModal = new bootstrap.Modal(document.getElementById('statusModal'));

    // Charger initialement la file d'attente
    loadQueue();
    loadIngredients();

    // Rafraîchir la file d'attente
    refreshBtn.addEventListener('click', function() {
        loadQueue();
        loadIngredients();
    });

    // Charger la file d'attente via API
    function loadQueue() {
        fetch('/api/order-items/queue')
            .then(response => response.json())
            .then(items => {
                renderQueue(items);
            })
            .catch(error => {
                console.error('Error loading queue:', error);
                queueContainer.innerHTML = `
                    <div class="alert alert-danger">
                        Erreur lors du chargement de la file d'attente
                    </div>
                `;
            });
    }

    // Afficher la file d'attente
    function renderQueue(items) {
        if (items.length === 0) {
            queueContainer.innerHTML = `
                <div class="alert alert-info">
                    Aucun item en attente de préparation
                </div>
            `;
            return;
        }

        let html = '';
        items.forEach(item => {
            html += `
                <div class="queue-item ${item.status}">
                    <div class="queue-item-header">
                        <span class="queue-item-id">#${item.id}</span>
                        <span class="queue-item-status status-${item.status}">${item.status}</span>
                    </div>
                    <div class="queue-item-body">
                        <p><strong>${item.ingredient.name}</strong> x ${item.quantity}</p>
                        ${item.specialInstruction ? `<p class="text-muted"><small>${item.specialInstruction}</small></p>` : ''}
                        <p class="text-muted"><small>Commande #${item.order.id}</small></p>
                    </div>
                    <div class="queue-item-actions">
                        <button class="btn btn-sm btn-outline-primary change-status" 
                                data-item-id="${item.id}">
                            <i class="bi bi-pencil"></i> Modifier statut
                        </button>
                    </div>
                </div>
            `;
        });
        queueContainer.innerHTML = html;

        // Ajouter les écouteurs d'événements
        document.querySelectorAll('.change-status').forEach(btn => {
            btn.addEventListener('click', function() {
                currentItemId = this.getAttribute('data-item-id');
                statusModal.show();
            });
        });
    }

    // Charger le monitoring des ingrédients
    function loadIngredients() {
        fetch('/api/order-items/ingredients')
            .then(response => response.text())
            .then(data => {
                ingredientsMonitoring.innerHTML = data;
            });
    }

    // Confirmer le changement de statut
    document.getElementById('confirmStatusChange').addEventListener('click', function() {
        if (!currentItemId) return;

        const newStatus = document.getElementById('statusSelect').value;

        fetch(`/api/order-items/${currentItemId}/status?newStatus=${newStatus}`, {
            method: 'PUT'
        })
            .then(response => {
                if (response.ok) {
                    loadQueue(); // Recharger la file d'attente
                    statusModal.hide();
                }
            });
    });
});