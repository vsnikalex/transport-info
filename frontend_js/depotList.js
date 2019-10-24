const React = require('react');
const ReactDOM = require('react-dom');

const axios = require('axios');

class DepotApp extends React.Component {

    constructor(props) {
        super(props);
        this.state = {depots: []};
    }

    componentDidMount() {
        axios.get('api/depot/all').then(response => {
            this.setState({depots: response.data});
        });
    }

    render() {
        return (
            <DepotList depots={this.state.depots}/>
        )
    }
}

class DepotList extends React.Component{
    render() {
        const depots = this.props.depots.map(depot =>
            <Depot key={depot.id} depot={depot}/>
        );
        return (
            <div className="form-input-set">
                <label htmlFor="selectbox">Depot</label>
                    <select name="select" id="selectbox" className="form-select">
                        {depots}
                </select>
            </div>
        )
    }
}

class Depot extends React.Component{
    render() {
        return (
            <option value={this.props.depot.id}>{this.props.depot.location.country} ({this.props.depot.type})</option>
        )
    }
}

ReactDOM.render(
    <DepotApp />,
    document.getElementById('depots')
);