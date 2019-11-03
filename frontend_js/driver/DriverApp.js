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
    }

    selectDriver(event) {
        console.log('selected driver id: ' + event.target.value);

        const driver = this.state.drivers.find(driver =>
            driver.id == event.target.value
        );

        this.setState({selectedDriverId: event.target.value, selectedDriver: driver});
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
                    <Route deliveryDTO={this.state.selectedDriver.deliveryDTO} />
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
            points.push(<Point key={coords} coords={coords} operations={operations}/>);
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

        const loads = this.props.operations.loadOps.map(load =>
            <Load key={load.id} load={load}/>
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

        // for unfinished sections: className="badge badge-brand"

        return (
            <div>
                <Collapsible trigger={
                    <a className="content-list-item" data-toggle="button">
                        <span className="badge">{this.props.operations.unloadOps.length}</span>
                        <span className="badge">{this.props.operations.loadOps.length}</span>
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
            isChecked: (this.props.load.status === 'SHIPPED')
        };

        this.handleChecked = this.handleChecked.bind(this);
    }

    // for loaded cargoes style="background-color: #00CC00", button disabled without script
    handleChecked() {
        this.setState({isChecked: !this.state.isChecked});
    }

    render() {
        let button;
        let style;
        if (this.state.isChecked) {
            button = <button className="btn btn-default btn-small" disabled={true}>shipped</button>;
            style = {backgroundColor: "#00CC00"};
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
            isChecked: (this.props.unload.status === 'DELIVERED')
        };

        this.handleChecked = this.handleChecked.bind(this);
    }

    // for loaded cargoes style="background-color: #00CC00", button disabled without script
    handleChecked() {
        this.setState({isChecked: !this.state.isChecked});
    }

    render() {
        let button;
        let style;
        if (this.state.isChecked) {
            button = <button className="btn btn-default btn-small" disabled={true}>delivered</button>;
            style = {backgroundColor: "#00CC00"};
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