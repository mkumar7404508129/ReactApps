/**
 * @format
 */
import App from './App';
import {name as appName} from './app.json';
import base64 from 'base64-js'
import {decode,encode} from 'base-64'

import React,{useState} from 'react'
import {AppRegistry, StyleSheet, Text, View,NativeModules,Image,NativeEventEmitter} from 'react-native'
const HellowCheck = NativeModules.HellowCheck


const cameraEmitter = new NativeEventEmitter(HellowCheck)
var isImageUpdated=true;
const HelloWorld = () => {
  const [imageUri, setImageUri] = useState(null)

  const  getImage=()=>{
      cameraEmitter.addListener('cameraFrame', event => {
  const url = 'data:image/png;base64,' + event
  if(isImageUpdated){
    handleLoadImage(url)
    isImageUpdated=false;
  }
})

      HellowCheck.sayHellow("Nripender",(err,message)=>{
        if(message){
        
          console.log(message)

        }
        else{
          console.log(err)
        }
      })
    }
const handleLoadImage = (URL) => {
  console.log("function called")
  const uri = URL // Example image URI
  setImageUri(uri) // Set the image URI in the state variable
}   
const updateValue=()=>{
  isImageUpdated=true
}
  return (
    <View style={styles.container}>
      {/* <Image source={{uri: `data:image/png;base64,${message}`}} /> */}
      { imageUri && <Image source={{uri: imageUri}} 
                           style={{width: 200, height: 200}}
                           onLoad={updateValue}
                                 />}
      <Text onPress={getImage} style={styles.hello}>Hello, World</Text>
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
AppRegistry.registerComponent(appName, () => HelloWorld)
