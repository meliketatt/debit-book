import React, { Component } from 'react';
import { Alert, Button, TextInput, View, ToastAndroid, AsyncStorage } from 'react-native';
import axios from 'axios';

import styles from './LoginCss';

export default class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: '',
        };
    }

    static navigationOptions = {
        title: 'Login'
    };

    onLogin() {
        axios.get('http://192.168.1.106:8080/oauth/token?grant_type=password&username=' + this.state.username + '&password=' + this.state.password, {
           auth: {username: 'stajokulu', password: '123456'}
        }).then(res => {            
            axios.get('http://192.168.1.106:8080/debit/get-current-user', {
                headers: {
                    Authorization: 'Bearer ' + res.data.access_token
                }
            } ).then(resp => {
               // ToastAndroid.show('User Geldi.', ToastAndroid.SHORT);
                if (resp.data.type === 'CUSTOMER') {
                    this.props.navigation.navigate('Home');
                    ToastAndroid.show('Customer Geldi.', ToastAndroid.SHORT);
                }else {
                    this.props.navigation.navigate('ProducerHome');
                    ToastAndroid.show('Producer Geldi.', ToastAndroid.SHORT);
                }
            }) 
            
            AsyncStorage.setItem('token', JSON.stringify(res.data));  
            //ToastAndroid.show('Giriş Başarılı.', ToastAndroid.SHORT);
            
            // console.log(AsyncStorage.getItem('token'));
            //this.props.navigation.navigate('Home');
        }).catch(error =>{
            Alert.alert('Başarısız!');
            console.log(error);
        }); 
    }

    render() {
        return (
            <View style={styles.container}>
                <TextInput
                    value={this.state.username}
                    onChangeText={(u) => this.setState({ username: u })}
                    placeholder={'Username'}
                    style={styles.input}
                />
                <TextInput
                    value={this.state.password}
                    onChangeText={(p) => this.setState({ password: p })}
                    placeholder={'Password'}
                    secureTextEntry={true}
                    style={styles.input}
                />

                <Button
                    title={'Login'}
                    style={styles.input}
                    onPress={this.onLogin.bind(this)}
                />

                <Button
                    title={'Register'}
                    style={styles.input}
                    onPress={() => this.props.navigation.navigate('Register')}
                />
            </View>
        );
    }
}