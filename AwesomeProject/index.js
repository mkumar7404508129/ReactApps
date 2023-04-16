/**
 * @format
 */
import App from './App';
import {name as appName} from './app.json';



import React from 'react'
import {AppRegistry, StyleSheet, Text, View,NativeModules,Image} from 'react-native'
import {atob} from 'base-64'

const {HellowCheck} = NativeModules

const HelloWorld = () => {
   
        HellowCheck.sayHellow("Nripender",(error,message)=>{
         if(error){
            console.log(error)
         }
         else{
            console.log(message.data)
            const byteArray = Uint8Array.from(atob(message.data), c => c.charCodeAt(0))
        // Convert the byte array to a Blob object
            const blob = new Blob([byteArray], {type: 'image/png'})
            const imageUrl = URL.createObjectURL(blob)
            return <Image source={{uri:imageUrl}} style={{width: 200, height: 200}} />
         }
        })

  return (
    <View style={styles.container}>
      <Text  style={styles.hello}>Hello, World</Text>
    </View>
  )
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
})

// AppRegistry.registerComponent(appName, () => App);
AppRegistry.registerComponent(appName, () => HelloWorld)
