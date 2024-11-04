const express = require('express');
const cors = require('cors');
const app = express();
const port = 3000;

// Activer CORS pour autoriser les requêtes provenant d'autres domaines
app.use(cors());

// Définir une route GET simple pour tester la connexion
app.get('/api/test', (req, res) => {
    res.json({ message: "Ce message est ereçu depuis le backend" });
});

// Démarrer le serveur
app.listen(port, () => {
    console.log(`Serveur Node.js démarré sur http://localhost:${port}`);
});
