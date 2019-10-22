const axios = require('axios');

axios.get('api/depot/all')
    .then(res => {
        console.log(res.data);
    })
    .catch(err => {
        console.log(err);
    });