import axios from 'axios';
import { AsyncStorage } from 'react-native';

const customaxios = axios.create({
    baseURL: 'http://192.168.1.106:8080/'
});

customaxios.interceptors.request.use(
    function (config) {
        let token = AsyncStorage.getItem('token').access_token;
        if (token) config.headers.Authorization = `Bearer ${token}`;
        return config;
    },
    function (error) {
        return Promise.reject(error);
    }
);

const _getStorageValue = async (key) => {
    try {
        const retrievedItem = await AsyncStorage.getItem(key);
        const item = JSON.parse(retrievedItem);
        return item;
    } catch (error) {
        console.log(error.message);
    }
    return
}

export default customaxios;