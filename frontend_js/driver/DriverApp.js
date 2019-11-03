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

        console.log('update DriverApp state');
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
                           updateLoadOpStatus={this.updateLoadOpStatus} />
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
            <div className="col-l-4 demo-col">
                {select}
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
        console.log('render Route');

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
                               updateLoadOpStatus={this.props.updateLoadOpStatus} />);
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
    }

    updateLoadOpStatus(id, status) {
        this.props.updateLoadOpStatus(this.props.coords, id, status);
    }

    componentDidMount() {
        axios.get('api/depot/' + this.props.coords + '/').then(response => {
            this.setState({depot: response.data});
        });
    }

    render() {
        console.log('render Point');

        if (!this.state.depot.location || typeof this.state.depot.location === 'undefined') {
            return null;
        }

        const loads = this.props.operations.loadOps.map(load =>
            <Load key={load.id} load={load} updateLoadOpStatus={this.updateLoadOpStatus} />
        );
        let loadsSection;
        if (loads.length > 0) {
            loadsSection = <div><a className="content-list-item">load:</a>{loads}</div>
        } else {
            loadsSection = null;
        }

        const unloads = this.props.operations.unloadOps.map(unload =>
            <Unload key={unload.id} unload={unload}/>
        );
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
                        <span className="badge">&#9660;{this.props.operations.unloadOps.length}</span>
                        <span className="badge">&#9650;{this.props.operations.loadOps.length}</span>
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
        this.state = {
            isChecked: (props.load.status !== 'PREPARED')
        };

        this.handleChecked = this.handleChecked.bind(this);
    }

    handleChecked() {
        axios.put('api/cargo/update/status/' + this.props.load.id + '/SHIPPED')
            .then(response => {
                // TODO: change state in both this and connected Point
                this.props.updateLoadOpStatus(this.props.load.id, 'SHIPPED');
            })
            .catch(error => {
                console.log(error);
            });

        this.setState({isChecked: !this.state.isChecked});
    }

    render() {
        console.log('render Load');

        let button;
        let style;
        if (this.state.isChecked) {
            if (this.props.load.status === 'SHIPPED') {
                button = <button className="btn btn-default btn-small" disabled={true}>shipped</button>;
                style = {backgroundColor: "#009600"};
            } else if (this.props.load.status === 'DELIVERED') {
                button = <button className="btn btn-default btn-small" disabled={true}>delivered</button>;
                style = {backgroundColor: "#009600"};
            }
        } else {
            button = <button className="btn btn-default btn-small" onClick={this.handleChecked}>ship</button>;
            style = null;
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
        this.state = {
            isChecked: (props.unload.status !== 'SHIPPED')
        };

        this.handleChecked = this.handleChecked.bind(this);
    }

    handleChecked() {
        axios.put('api/cargo/update/status/' + this.props.unload.id + '/DELIVERED')
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.log(error);
            });

        this.setState({isChecked: !this.state.isChecked});
    }

    render() {
        console.log('render Unload');

        let button;
        let style;
        if (this.state.isChecked && this.props.unload.status !== 'SHIPPED') {
            if (this.props.unload.status === 'PREPARED') {
                button = <button className="btn btn-default btn-small" disabled={true}>prepared</button>;
                style = {backgroundColor: "#f7f7f7"};
            } else if (this.props.unload.status === 'DELIVERED') {
                button = <button className="btn btn-default btn-small" disabled={true}>delivered</button>;
                style = {backgroundColor: "#009600"};
            }
        } else {
            button = <button className="btn btn-default btn-small" onClick={this.handleChecked}>deliver</button>;
            style = null;
        }

        return(
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