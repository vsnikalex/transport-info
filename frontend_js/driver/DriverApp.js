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
            selectedDriver: {}
        };

        this.selectDriver = this.selectDriver.bind(this);
        this.updateLoadOpStatus = this.updateLoadOpStatus.bind(this);
        this.updateUnloadOpStatus = this.updateUnloadOpStatus.bind(this);
    }

    selectDriver(event) {
        const driver = this.state.drivers.find(driver =>
            driver.id == event.target.value
        );

        this.setState({selectedDriverId: event.target.value, selectedDriver: driver});
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

        let unloadOps = operations[coords].unloadOps;
        let index = unloadOps.findIndex(cargo => cargo.id == id);
        unloadOps[index].status = status;

        for (let coords in operations){
            let i = operations[coords].loadOps.findIndex(cargo => cargo.id == id);
            if (i !== -1) {
                operations[coords].loadOps[i].status = status;
            }
        }

        this.setState({selectedDriver: driver});
    }

    componentDidMount() {
        axios.get('api/driver/all').then(response => {
            this.setState({drivers: response.data});
        });
    }

    render() {
        return (
            <div className="container-fixed demo-grid">
                <div className="row">
                    <Route deliveryDTO={this.state.selectedDriver.deliveryDTO}
                           updateLoadOpStatus={this.updateLoadOpStatus}
                           updateUnloadOpStatus={this.updateUnloadOpStatus} />
                    <div className="col-l-4 demo-col">
                        ADD INFO
                    </div>
                    <DriverList drivers={this.state.drivers}
                                selectedDriverId={this.state.selectedDriverId}
                                selectDriver={this.selectDriver} />
                </div>
            </div>
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

                <h2 className="underline">
                    <div className="row">
                        <div className="col-l-6">Activities</div>
                        <div className="col-l-6">
                            <button className="pager pager-block">START / FINISH</button>
                        </div>
                    </div>
                </h2>
                <div className="content-list">
                    <a className="content-list-item content-list-item-brand">
                        <div className="row">
                            <div className="col-l-6">DRIVER</div>
                            <div className="col-l-2"></div>
                            <div className="col-l-3">01:35:22</div>
                        </div>
                    </a>
                    <a className="content-list-item">SECOND DRIVER</a>
                    <a className="content-list-item">LOAD</a>
                    <a className="content-list-item">UNLOAD</a>
                </div>
            </div>
        )
    }
}

class Driver extends React.Component {
    render() {
        return (
            <option value={this.props.driver.id}>
                {this.props.driver.firstName} {this.props.driver.lastName}
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