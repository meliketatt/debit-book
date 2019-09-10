import React, { Component } from 'react';
import { Text, View, FlatList, StyleSheet, TouchableOpacity  } from 'react-native';

import axios from '../../../axiosCustom';

export default class ProducerHome extends Component {

    state = {
        recordList: []
    }

    static navigationOptions = {
        title: 'ProducerHome',
    };

    componentDidMount() {
        axios.get('debit/record/save').then(resp => {
            this.setState({ recordList: resp.data });
        }).catch(error => console.log(error))
    }


    render() {
        return (
            <View style={styles.container}>
                <TouchableOpacity  style={styles.addAppoBtn} onPress={() => this.props.navigation.navigate('AddRecord')}>
                    <Text>+</Text>
                </TouchableOpacity >
                <FlatList data={this.state.recordList} renderItem={(item) => <Text style={styles.item}>{item.price}</Text>} />
            
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