const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

import Collapsible from 'react-collapsible';

class DriverApp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            drivers: [],
            selectedDriverId: 0,
            selectedDriver: {},
            userIsDriver: false
        };

        this.selectDriver = this.selectDriver.bind(this);
        this.updateDriverActivity = this.updateDriverActivity.bind(this);
        this.updateLoadOpStatus = this.updateLoadOpStatus.bind(this);
        this.updateUnloadOpStatus = this.updateUnloadOpStatus.bind(this);
    }

    selectDriver(event) {
        const driver = this.state.drivers.find(driver =>
            driver.id == event.target.value
        );

        this.setState({selectedDriverId: event.target.value, selectedDriver: driver});
    }

    updateDriverActivity(activity) {
        let driver = this.state.selectedDriver;
        driver.action = activity;
        this.setState({selectedDriver: driver});
    }

    updateLoadOpStatus(coords, id, status) {
        let driver = this.state.selectedDriver;
        let operations = driver.deliveryDTO.routeWithCargoOperations;

        let loadOps = operations[coords].loadOps;
        let index = loadOps.findIndex(cargo => cargo.id == id);
        loadOps[index].status = status;

        for (let coords in operations){
            let i = operations[coords].unloadOps.findIndex(cargo => cargo.id == id);
            if (i !== -1) {
                operations[coords].unloadOps[i].status = status;
            }
        }

        this.setState({selectedDriver: driver});
    }

    updateUnloadOpStatus(coords, id, status) {
        let driver = this.state.selectedDriver;
        let operations = driver.deliveryDTO.routeWithCargoOperations;

        for (let coords in operations){
            let i = operations[coords].loadOps.findIndex(cargo => cargo.id == id);
            if (i !== -1) {
                operations[coords].loadOps[i].status = status;
            }
        }

        let unloadOps = operations[coords].unloadOps;
        let index = unloadOps.findIndex(cargo => cargo.id == id);
        unloadOps[index].status = status;

        this.setState({selectedDriver: driver});
    }

    componentDidMount() {
        axios.get('/transport/getMyDriverId').then(id => {
            if (id.data !== -1) {
                axios.get('/transport/driver/api/mydata/' + id.data).then(driver => {
                    this.setState({selectedDriver: driver.data,
                                   selectedDriverId: id.data,
                                   userIsDriver: true});
                });
            } else {
                axios.get('api/driver/all').then(response => {
                    this.setState({drivers: response.data});
                });
            }
        });
    }

    render() {
        let activities;
        let info;
        if (!this.state.selectedDriver.deliveryDTO || typeof this.state.selectedDriver.deliveryDTO === 'undefined') {
            activities = <div className="col-l-4 ">
                            <h2 className="underline">Activities Log</h2>
                        </div>;
            info =      <div className="col-l-4 ">
                            <h2 className="underline">Add Info</h2>
                        </div>;
        } else {
            activities = <ActivitiesList driverActivity={this.state.selectedDriver.action}
                                         driverId={this.state.selectedDriver.id}
                                         truckId={this.state.selectedDriver.truckDTO.id}
                                         updateDriverActivity={this.updateDriverActivity} />;
            info = <Info truckId={this.state.selectedDriver.deliveryDTO.truckID} />;
        }

        let driverList;
        if (this.state.userIsDriver) {
            driverList =    null;
        } else {
            driverList =    <div className="row">
                                <DriverList drivers={this.state.drivers}
                                            selectedDriverId={this.state.selectedDriverId}
                                            selectDriver={this.selectDriver} />
                            </div>;
        }

        return (
            <div className="container-fixed demo-grid">

                {driverList}

                <div className="tc-example">
                    <div className="row">
                        <Route deliveryDTO={this.state.selectedDriver.deliveryDTO}
                               updateLoadOpStatus={this.updateLoadOpStatus}
                               updateUnloadOpStatus={this.updateUnloadOpStatus} />
                        {info}
                        {activities}
                    </div>
                </div>
            </div>
        )
    }
}

class Info extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            truck: {}
        };
    }

    componentDidMount() {
        axios.get('api/truck/' + this.props.truckId).then(response => {
            this.setState({truck: response.data});
        });
    }

    render() {
        let coworkers = [];
        let info = this.state.truck.driversInfo;
        for (let index in info){
            let driver = info[index];
            coworkers.push(<div className="media-hint">#{driver}</div>);
        }

        return (
            <div className="col-l-4">
                <h2 className="underline">Add Info</h2>
                <ul className="content-list">
                    <li className="media">
                        <div className="media-body">
                            <div className="media-heading">Truck</div>
                            <div className="media-hint">Plate: {this.state.truck.plate}</div>
                            <div className="media-hint">Capacity: {this.state.truck.capacity}kg</div>
                        </div>
                    </li>
                    <li className="media">
                        <div className="media-body">
                            <div className="media-heading">Drivers</div>
                            {coworkers}
                        </div>
                    </li>
                </ul>
            </div>
        )
    }
}

class ActivitiesList extends React.Component {
    constructor(props) {
        super(props);

        this.finishCurrentActivity = this.finishCurrentActivity.bind(this);
    }

    finishCurrentActivity() {
        let now = Math.round(new Date().getTime() / 1000);
        console.log('finish activity at ' + now);
        axios.get('api/task/finish/' + this.props.driverId + '/' + now)
            .then(response => {
                console.log('activity finished at: ' + response.data);
                this.props.updateDriverActivity('REST');
            })
            .catch(error => {
                console.log(error);
            });
    }

    render() {
        if (!this.props.driverActivity || typeof this.props.driverActivity === 'undefined') {
            return (
                <div className="col-l-4 ">
                    <h2 className="underline">Activities Log</h2>
                </div>
            )
        }
        console.log(this.props.driverActivity);

        let driverActivity = this.props.driverActivity;
        let driverId = this.props.driverId;
        let truckId = this.props.truckId;
        let updateDriverActivity = this.props.updateDriverActivity;
        let finishCurrentActivity = this.finishCurrentActivity;
        const acts = ['DRIVE', 'HELP', 'LOAD', 'UNLOAD'];
        const activities = acts.map(function (act) {
            return <Activity key={act} thisActivity={act} driverId={driverId} truckId={truckId}
                             driverActivity={driverActivity} updateDriverActivity={updateDriverActivity}
                             finishCurrentActivity={finishCurrentActivity} />
        });

        return (
            <div className="col-l-4">
                <h2 className="underline">
                    <div className="row">
                        <div className="col-l-6">Activities</div>
                        <div className="col-l-6">
                            <button className="pager pager-block" onClick={this.finishCurrentActivity}>FINISH</button>
                        </div>
                    </div>
                </h2>
                <div className="content-list">
                    {activities}
                </div>
            </div>
        )
    }
}

class Activity extends React.Component {
    constructor(props) {
        super(props);

        this.startActivity = this.startActivity.bind(this);
        this.checkThenStartActivity = this.checkThenStartActivity.bind(this);
    }

    checkThenStartActivity() {
        if (this.props.thisActivity !== 'REST') {
            this.props.finishCurrentActivity();
            this.startActivity();
        } else {
            this.startActivity();
        }
    }

    startActivity() {
        let now = Math.round(new Date().getTime() / 1000);
        console.log('start activity at ' + now);
        axios.get('api/task/start/' + this.props.thisActivity + '/' + now + '/' + this.props.driverId + '/' + this.props.truckId)
            .then(response => {
                console.log('activity started at: ' + response.data);
                this.props.updateDriverActivity(this.props.thisActivity);
            })
            .catch(error => {
                console.log(error);
            });
    }

    render() {
        let title;
        switch (this.props.thisActivity) {
            case 'DRIVE':
                title = 'DRIVER';
                break;
            case 'HELP':
                title = 'SECOND DRIVER';
                break;
            case 'LOAD':
                title = 'LOAD';
                break;
            case 'UNLOAD':
                title = 'UNLOAD';
                break;
        }

        let aClass;
        if (this.props.thisActivity === this.props.driverActivity) {
            aClass = "content-list-item content-list-item-brand";
        } else {
            aClass = "content-list-item";
        }

        return (
            <a className={aClass} onClick={this.checkThenStartActivity}>
                <div className="row">
                    <div className="col-l-6">{title}</div>
                    <div className="col-l-2"></div>
                    <div className="col-l-3"></div>
                </div>
            </a>
        )
    }
}

class DriverList extends React.Component {
    render() {
        const drivers = this.props.drivers.map(driver =>
            <Driver key={driver.id} driver={driver}/>
        );

        let select;
        if (this.props.selectedDriverId == 0) {
            select = <select defaultValue={0} onChange={this.props.selectDriver}>
                        <option value={0} disabled>Choose Driver</option>
                        {drivers}
                    </select>
        } else {
            select = <select value={this.props.selectedDriverId} onChange={this.props.selectDriver}>
                        {drivers}
                    </select>
        }

        return (
            <div className="col-l-4">
                {select}
            </div>
        )
    }
}

class Driver extends React.Component {
    render() {
        return (
            <option value={this.props.driver.id}>
                #{this.props.driver.id} {this.props.driver.firstName} {this.props.driver.lastName}
                ({this.props.driver.location.country})
            </option>
        )
    }
}

class Route extends React.Component {
    render() {
        if (!this.props.deliveryDTO || typeof this.props.deliveryDTO === 'undefined') {
            return (
                <div className="col-l-4 ">
                    <h2 className="underline">Delivery is not assigned</h2>
                </div>
            )
        }

        let points = [];
        const routeMap = this.props.deliveryDTO.routeWithCargoOperations;

        for (let coords in routeMap){
            let operations = routeMap[coords];
            points.push(<Point key={coords} coords={coords} operations={operations}
                               updateLoadOpStatus={this.props.updateLoadOpStatus}
                               updateUnloadOpStatus={this.props.updateUnloadOpStatus} />);
        }

        return (
            <div className="col-l-4 ">
                <h2 className="underline">Delivery #{this.props.deliveryDTO.id}</h2>
                <div className="content-list">
                    {points}
                </div>
            </div>
        )
    }
}

class Point extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            depot: {}
        };

        this.updateLoadOpStatus = this.updateLoadOpStatus.bind(this);
        this.updateUnloadOpStatus = this.updateUnloadOpStatus.bind(this);
    }

    updateLoadOpStatus(id, status) {
        this.props.updateLoadOpStatus(this.props.coords, id, status);
    }

    updateUnloadOpStatus(id, status) {
        this.props.updateUnloadOpStatus(this.props.coords, id, status);
    }

    componentDidMount() {
        axios.get('api/depot/' + this.props.coords + '/').then(response => {
            this.setState({depot: response.data});
        });
    }

    render() {
        if (!this.state.depot.location || typeof this.state.depot.location === 'undefined') {
            return null;
        }

        let shipped = 0;
        const loads = this.props.operations.loadOps.map(load => {
            if (load.status === 'SHIPPED' || load.status === 'DELIVERED') {
                shipped++;
            }
            return <Load key={load.id} load={load} updateLoadOpStatus={this.updateLoadOpStatus}/>;
        });
        let loadBadge;
        if (shipped === this.props.operations.loadOps.length) {
            loadBadge = "badge";
        } else {
            loadBadge = "badge badge-brand";
        }
        let loadsSection;
        if (loads.length > 0) {
            loadsSection = <div><a className="content-list-item">load:</a>{loads}</div>
        } else {
            loadsSection = null;
        }

        let delivered = 0;
        const unloads = this.props.operations.unloadOps.map(unload => {
            if (unload.status === 'DELIVERED') {
                delivered++;
            }
            return <Unload key={unload.id} unload={unload} updateUnloadOpStatus={this.updateUnloadOpStatus}/>;
        });
        let unloadBadge;
        if (delivered === this.props.operations.unloadOps.length) {
            unloadBadge = "badge";
        } else {
            unloadBadge = "badge badge-brand";
        }
        let unloadsSection;
        if (unloads.length > 0) {
            unloadsSection = <div><a className="content-list-item">unload:</a>{unloads}</div>
        } else {
            unloadsSection = null;
        }

        return (
            <div>
                <Collapsible trigger={
                    <a className="content-list-item" style={{backgroundColor: "#d9d9d9"}}>
                        <span className={unloadBadge}>&#9660;{this.props.operations.unloadOps.length}</span>
                        <span className={loadBadge}>&#9650;{this.props.operations.loadOps.length}</span>
                        {this.state.depot.location.country}: {this.state.depot.location.city}, {this.state.depot.location.name}
                    </a>
                }>
                <div>
                    {loadsSection}
                    {unloadsSection}
                </div>
                </Collapsible>
            </div>
        )
    }
}

class Load extends React.Component {
    constructor(props) {
        super(props);

        this.ship = this.ship.bind(this);
    }

    ship() {
        axios.put('api/cargo/update/status/' + this.props.load.id + '/SHIPPED')
            .then(response => {
                this.props.updateLoadOpStatus(this.props.load.id, 'SHIPPED');
            })
            .catch(error => {
                console.log(error);
            });
    }

    render() {
        let button;
        let style;

        switch (this.props.load.status) {
            case 'PREPARED':
                button = <button className="btn btn-default btn-small" onClick={this.ship}>ship</button>;
                style = null;
                break;
            case 'SHIPPED':
                button = <button className="btn btn-default btn-small" disabled={true}>shipped</button>;
                style = {backgroundColor: "#00aa00"};
                break;
            case 'DELIVERED':
                button = <button className="btn btn-default btn-small" disabled={true}>delivered</button>;
                style = {backgroundColor: "#00aa00"};
                break;
        }

        return(
            <a className="content-list-item" style={style}>
                <div className="row">
                    <div className="col-l-8">
                        {this.props.load.description} {this.props.load.weight}kg
                    </div>
                    <div className="col-l-2">
                        {button}
                    </div>
                </div>
            </a>
        )
    }
}

class Unload extends React.Component {
    constructor(props) {
        super(props);

        this.deliver = this.deliver.bind(this);
    }

    deliver() {
        axios.put('api/cargo/update/status/' + this.props.unload.id + '/DELIVERED')
            .then(response => {
                this.props.updateUnloadOpStatus(this.props.unload.id, 'DELIVERED');
            })
            .catch(error => {
                console.log(error);
            });
    }

    render() {
        let button;
        let style;
        switch (this.props.unload.status) {
            case 'PREPARED':
                button = <button className="btn btn-default btn-small" disabled={true}>prepared</button>;
                style = {backgroundColor: "#f7f7f7"};
                break;
            case 'SHIPPED':
                button = <button className="btn btn-default btn-small" onClick={this.deliver}>deliver</button>;
                style = null;
                break;
            case 'DELIVERED':
                button = <button className="btn btn-default btn-small" disabled={true}>delivered</button>;
                style = {backgroundColor: "#00aa00"};
                break;
        }

        return (
            <a className="content-list-item" style={style}>
                <div className="row">
                    <div className="col-l-8">
                        {this.props.unload.description} {this.props.unload.weight}kg
                    </div>
                    <div className="col-l-2">
                        {button}
                    </div>
                </div>
            </a>

        )
    }
}

ReactDOM.render(
    <DriverApp/>,
    document.getElementById('driver_app')
);
