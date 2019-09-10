import React, { Component } from 'react';
import { Text, View, FlatList, StyleSheet, TouchableOpacity  } from 'react-native';

import axios from 'axios';

export default class Home extends Component {

    state = {
        appointmentList: []
    }

    static navigationOptions = {
        title: 'Home',
    };

    componentDidMount() {
        axios.get('http://192.168.1.106:8080/debit/record/save').then(resp => {
            this.setState({ appointmentList: resp.data });
        }).catch(error => console.log(error))
    }


    render() {
        return (
            <View style={styles.container}>
                <TouchableOpacity  style={styles.addAppoBtn} onPress={() => this.props.navigation.navigate('AddAppointment')}>
                    <Text>+</Text>
                </TouchableOpacity >
                <FlatList data={this.state.appointmentList} renderItem={(item) => <Text style={styles.item}>{item.price}</Text>} />
            </View>
        )
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingTop: 22,
        justifyContent: 'center',
        alignItems: 'center',
    },
    item: {
        padding: 10,
        fontSize: 18,
        height: 44,
    },
    addAppoBtn: {
        position: 'absolute',
        right: 0,
        top: 0,
        padding: 10,
        backgroundColor: 'lightblue',
        textAlign: 'center',
        width: 50,
        height: 50,
        borderRadius: 100,
        justifyContent: 'center',
        alignItems: 'center',
    }
})