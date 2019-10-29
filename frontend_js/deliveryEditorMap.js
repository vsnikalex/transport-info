const L = require('leaflet');

const map = L.map('map').setView([48.43, 18.56], 4);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

const depotLayerGroup = L.layerGroup().addTo(map);
const cargoLayerGroup = L.layerGroup().addTo(map);

const depotMarkerStyle = {
    radius: 8,
    fillColor: "#ff7800",
    color: "#000",
    weight: 1,
    opacity: 1,
    fillOpacity: 0.8
};

export function markDepot(depot) {
    if (typeof depot === 'undefined') { return; }

    let loc = depot.location;

    let latLng = L.latLng(
        parseFloat(loc.point["lat"]), parseFloat(loc.point["lng"])
    );

    L.circleMarker(latLng, depotMarkerStyle)
        .bindPopup(loc.country + ' ' + depot.type)
        .addTo(depotLayerGroup);
}

export function clearDepots() {
    depotLayerGroup.clearLayers();
}

const cargoDestinationMarkerStyle = {
    radius: 6,
    fillColor: "#1367ed",
    color: "#000",
    weight: 1,
    opacity: 1,
    fillOpacity: 0.8
};

export function markCargoDestination(cargo) {
    if (cargo === null) { return; }

    let endLoc = cargo.endDepot.location;

    let latLng = L.latLng(
        parseFloat(endLoc.point["lat"]), parseFloat(endLoc.point["lng"])
    );

    let cargoMarker = L.circleMarker(latLng, cargoDestinationMarkerStyle)
        .bindPopup(endLoc.country + ' ' + cargo.endDepot.type);

    cargoMarker._id = cargo.id;

    cargoMarker.addTo(cargoLayerGroup);
}

export function clearCargoes() {
    cargoLayerGroup.clearLayers();
}

export function removeCargoMarker(id) {
    let cargoMarker = cargoLayerGroup.getLayers().find(layer =>
        layer._id == id
    );

    cargoLayerGroup.removeLayer(cargoMarker)
}