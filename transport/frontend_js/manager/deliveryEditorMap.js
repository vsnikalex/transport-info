const L = require('leaflet');
require('graphhopper-js-api-client');
const axios = require('axios');

const map = L.map('map').setView([48.43, 18.56], 4);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

const depotLayerGroup = L.layerGroup().addTo(map);
const cargoLayerGroup = L.layerGroup().addTo(map);
const truckLayerGroup = L.layerGroup().addTo(map);

const deliveryRoutingLayer = L.geoJSON().addTo(map);
let ghOptimization;

const truckRoutingLayer = L.geoJSON().addTo(map);
let ghRouting;

window.onload = function() {
    let apiKey = "9dcf0a7e-ee94-4b91-8966-ca7b35411a00";
    // let apiKey = "7ea230d6-d492-48bb-9ce0-06afb6c59ed3";
    let profile = "car";
    let host;

    ghOptimization = new GraphHopperOptimization({
        key: apiKey,
        host: host,
        vehicle: profile,
        elevation: false,
        optimize: true
    });

    ghRouting = new GraphHopperRouting({
        key: apiKey,
        host: host,
        vehicle: profile,
        elevation: false,
        optimize: true
    });
};

export function clearMapAndTable() {
    clearDepots();
    clearCargoes();
    clearDeliveryRoutes();
    clearTrucks();
    clearTruckRoute();
    clearInfoTable();
}

function fillDeliveryInfo(estDist, estTime) {
    document.getElementById("est_dist_delivery").innerHTML = estDist + 'km';
    document.getElementById("est_time_delivery").innerHTML = estTime + 'h';
}

function fillTruckInfo(estDist, estTime) {
    document.getElementById("est_dist_truck").innerHTML = estDist + 'km';
    document.getElementById("est_time_truck").innerHTML = estTime + 'h';
}

function fillRoute(activities) {
    let n = activities.length;
    let route = '';
    activities.forEach(a => {
        let coords = a.address.lat + ',' + a.address.lon;

        axios.get('api/depot/' + coords + '/').then(depot => {
            route += (n === activities.length ? '' : ' &rarr; ') + depot.data.location.city;

            if (--n === 0) {
                document.getElementById("est_route").innerHTML = route;
            }
        });
    });
}

function clearInfoTable() {
    document.getElementById("est_dist_delivery").innerHTML = '';
    document.getElementById("est_time_delivery").innerHTML = '';
    document.getElementById("est_dist_truck").innerHTML = '';
    document.getElementById("est_time_truck").innerHTML = '';
    document.getElementById("est_route").innerHTML = '';
}

export function clearDeliveryRoutes() {
    deliveryRoutingLayer.clearLayers();
}

export function optimizeDeliveryRoute() {
    clearDeliveryRoutes();
    ghOptimization.clear();

    let startDepot = depotLayerGroup.getLayers()[0];
    let startCoords = startDepot.getLatLng();
    ghOptimization.addPoint(new GHInput(startCoords.lat, startCoords.lng));

    let routePoints = cargoLayerGroup.getLayers();
    if (routePoints.length === 0) {
        return new Promise(function (resolve, reject) {
            fillDeliveryInfo(0, 0);
            resolve(0.0);
        })
    }
    routePoints.forEach(destination => {
        let destCoords = destination.getLatLng();
        ghOptimization.addPoint(new GHInput(destCoords.lat, destCoords.lng));
    });

    return ghOptimization.doTSPRequest()
        .then(function (response) {
                let routes = response.solution.routes[0];
                routes.points.forEach(line =>
                    deliveryRoutingLayer.addData({
                        "type" : "Feature",
                        "geometry" : line
                    })
                );

                let estDist = (response.solution.distance/1000).toFixed(1);
                let estTime = (response.solution.time/60/60).toFixed(1);
                fillDeliveryInfo(estDist, estTime);

                fillRoute(routes.activities);

                return [estTime, routes.activities];
            }
        ).catch(function(err){
            console.error(err.message);
        });
}

export function clearTruckRoute() {
    truckRoutingLayer.clearLayers();
}

export function calculateTruckRoute() {
    clearTruckRoute();
    ghRouting.clearPoints();

    let truck = truckLayerGroup.getLayers()[0];
    let truckCoords = truck.getLatLng();
    ghRouting.addPoint(new GHInput(truckCoords.lat, truckCoords.lng));

    let depot = depotLayerGroup.getLayers()[0];
    let depotCoords = depot.getLatLng();
    ghRouting.addPoint(new GHInput(depotCoords.lat, depotCoords.lng));

    return ghRouting.doRequest()
        .then(function (route) {
                let path = route.paths[0];
                truckRoutingLayer.addData({
                    "type" : "Feature",
                    "geometry" : path.points
                });

                let estDist = (route.paths[0].distance/1000).toFixed(1);
                let estTime = (route.paths[0].time/(1000*60*60)).toFixed(1);

                fillTruckInfo(estDist, estTime);

                return estTime;
            }
        ).catch(function(err){
            console.error(err.message);
        });
}

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
        .bindPopup(endLoc.country + ':' + endLoc.city + ' "' + endLoc.name + '" ' + cargo.endDepot.type);

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

const truckMarkerStyle = {
    radius: 6,
    fillColor: "#38f26a",
    color: "#000",
    weight: 1,
    opacity: 1,
    fillOpacity: 0.8
};

export function markTruck(truck) {
    if (typeof truck === 'undefined') { return; }

    let loc = truck.location;

    let latLng = L.latLng(
        parseFloat(loc.point["lat"]), parseFloat(loc.point["lng"])
    );

    L.circleMarker(latLng, truckMarkerStyle)
        .bindPopup(loc.city + ' ' + truck.plate)
        .addTo(truckLayerGroup);
}

export function clearTrucks() {
    truckLayerGroup.clearLayers();
}
