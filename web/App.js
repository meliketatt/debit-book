import { createAppContainer} from 'react-navigation';
import { createStackNavigator } from 'react-navigation-stack';

import Home from './src/components/Home/Home';
import Login from './src/components/Login/Login';
import Register from './src/components/Register/Register';
import AddRecord from './src/components/AddRecord/AddRecord';
import ProducerHome from './src/components/Producer/ProducerHome';

import {decode, encode} from 'base-64'


if (!global.btoa) {
global.btoa = encode;
}

if (!global.atob) {
global.atob = decode;
}

const MainNav = createStackNavigator({
    Login: Login,
    Register: Register,
    Home: Home,
    ProducerHome: ProducerHome,
    AddRecord : AddRecord
}, {
    initialRouteKey: 'Login'
})

export default createAppContainer(MainNav);
