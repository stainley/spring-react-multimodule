import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Home from "../components/Home/Home";
import ClientList from "../components/Client/ClientList";
import ClientEdit from "../components/Client/ClientEdit/ClientEdit";


class App extends Component {

    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/clients' exact={true} component={ClientList}/>
                    <Route path='/clients/:id' component={ClientEdit}/>
                </Switch>
            </Router>
        );
    }
}

export default App;
