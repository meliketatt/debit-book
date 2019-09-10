import React, { Component } from 'react';
import { ScrollView, Alert, TextInput, Button, Picker, ToastAndroid } from 'react-native';

import styles from '../Login/LoginCss';
import axios from 'axios';
import KeyboardShift from '../../../KeyboardShift';

export default class Register extends Component {

    constructor(props) {
        super(props);

        this.state = {
            name: '',
            surname: '',
            email: '',
            type: 'CUSTOMER',
            username: '',
            password: '',
        };
    }

    static navigationOptions = {
        title: 'Register',
    };

    onRegister() {
        const user = {
            name: this.state.name,
            surname: this.state.surname,
            email: this.state.email,
            type: this.state.type,
            username: this.state.username,
            password: this.state.password
        };
        axios.post('http://192.168.1.106:8080/registration', user).then(resp => {
            ToastAndroid.show('Kayıt Başarılı.', ToastAndroid.SHORT);
            this.props.navigation.navigate('Login');
        }).catch(error => {
            console.log(error);
            Alert.alert('Başarısız!')
        });
    }

    render() {
        return (
            <KeyboardShift>
                {() => (
                    <ScrollView contentContainerStyle={styles.container}>
                        <Picker selectedValue={this.state.type} style={styles.picker} onValueChange={itemVal => this.setState({ type: itemVal })}>
                            <Picker.Item label="Customer" value="CUSTOMER" />
                            <Picker.Item label="Producer" value="PRODUCER" />
                        </Picker>
                        <TextInput
                            value={this.state.name}
                            onChangeText={(n) => this.setState({ name: n })}
                            placeholder={'Name'}
                            style={styles.input}
                        />
                        <TextInput
                            value={this.state.surname}
                            onChangeText={(n) => this.setState({ surname: n })}
                            placeholder={'Surname'}
                            style={styles.input}
                        />
                        <TextInput
                            value={this.state.email}
                            onChangeText={(n) => this.setState({ email: n })}
                            placeholder={'Email'}
                            style={styles.input}
                        />
                         <TextInput
                            value={this.state.username}
                            onChangeText={(n) => this.setState({ username: n })}
                            placeholder={'Username'}
                            style={styles.input}
                        />
                        <TextInput
                            value={this.state.password}
                            onChangeText={(n) => this.setState({ password: n })}
                            placeholder={'Password'}
                            secureTextEntry={true}
                            style={styles.input}
                        />
                        <Button
                            title={'Register'}
                            style={styles.input}
                            onPress={this.onRegister.bind(this)}
                        />
                    </ScrollView>
                )}
            </KeyboardShift>
        )
    }
}