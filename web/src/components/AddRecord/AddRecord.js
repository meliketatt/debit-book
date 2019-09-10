import React, { Component } from 'react';
import { ToastAndroid, AsyncStorage, ScrollView, TextInput, Button, Picker } from 'react-native';

import styles from '../Login/LoginCss';
import axios from '../../../axiosCustom';
import KeyboardShift from '../../../KeyboardShift';

export default class AddRecord extends Component {
    state = {
        record: {
            explanation: '',
            customer:null,
            price: null
        },
        customers: [],
        token: null
    }

    static navigationOptions = {
        title: 'AddRecord',
    };

    componentDidMount() {        
        this.getCustomers();
    }

    getCustomers = () => {
        console.log("GetCustomers Çağrıldı");
        console.log(axios);
        
            axios.get('debit/get-customers').then(resp => {
                this.setState({customers: resp.data});
                console.log(resp.data);
                
            }).catch(error => {
                console.log(error);
                ToastAndroid.show('Customerlar getirilirken bir hata oluştu!', ToastAndroid.SHORT);
            })
    }

    addRecord = () => {
        axios.post('debit/record/save', this.state.record).then(resp => {
            this.props.navigation.navigate('Home');
        }).catch(error => {
            console.log(error);
            ToastAndroid.show('Kayıtta bir hata oluştu!', ToastAndroid.SHORT);
        })
    }

    selectCustomer = (val) => {
        this.setState({
            record: {
                customer: val
            }
        });
    }

    setPrice = (price) => {
        this.setState({record: {
            price: price,
            explanation: this.state.record.explanation,
            customer: this.state.record.customer
        }});
        console.log(this.state.record);
        
    }

    setExp = (exp) => {
        this.setState({record: {
            explanation: exp,
            price: this.state.record.price,
            customer: this.state.record.customer
        }})
    }

    setCustomer = (exp) => {
        this.setState({record: {
            explanation: this.state.record.explanation,
            price: this.state.record.price,
            customer: exp
        }})
    }

    render() {
        return (
            <KeyboardShift>
                {() => (
                    <ScrollView contentContainerStyle={styles.container}>
                        <Picker selectedValue={this.state.record.customer} style={styles.picker} onValueChange={itemVal => this.setCustomer(itemVal)}>
                            { this.state.customers.map(a => {
                                return <Picker.Item key={a.userId} label={a.name + ' ' + a.surname} value={a} />
                            }) }
                        </Picker>
                        <TextInput
                            value={this.state.record.explanation}
                            onChangeText={(n) => this.setExp(n)}
                            placeholder={'Explanation'}
                            style={styles.input}
                            keyboardType="default"
                        />
                        <TextInput
                            value={this.state.record.price}
                            onChangeText={(n) => this.setPrice(n)}
                            placeholder={'Price'}
                            style={styles.input}
                            keyboardType="number-pad"
                        />
                        <Button
                            title={'Save'}
                            style={styles.input}
                            onPress={this.addRecord}
                        />
                    </ScrollView>
                )}
            </KeyboardShift>
        )
    }
}