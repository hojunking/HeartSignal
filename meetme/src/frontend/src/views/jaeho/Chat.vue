<template>
  <div id="app">
    유저이름: 
    <input v-model="userName" type="text" >
    내용: <input v-model="message" type="text" @keyup="sendMessage">
    <div v-for="(item, idx) in recvList" :key="idx">
      <h3>유저이름: {{ item.userName }}</h3>
      <h3>내용: {{ item.content }}</h3>
    </div>
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import { ref } from 'vue'

export default {
    name: 'App',
    setup() {
        const userName = ref("");
        const message = ref("");
        const recvList = ref([]);

        const sendMessage = (e) => {
            if(e.keyCode === 13 && userName.value !== '' && message.value !== ''){
                send()
                message.value = ''
            }
        }
        const send = () => {
            console.log("Send message:" + message.value);
            if (stompClient && stompClient.connected) {
                const msg = { 
                    userName: userName.value,
                    content: message.value 
                };
                stompClient.send("/receive", JSON.stringify(msg), {});
            }
        }
        





        // 연결
        let stompClient = null;
        const connect = () => {
            const serverURL = "http://192.168.0.75:8000/ws"
            let socket = new SockJS(serverURL);
            stompClient = Stomp.over(socket);
            console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
            stompClient.connect(
                {},
                frame => {
                // 소켓 연결 성공
                    stompClient.connected = true;
                    console.log('소켓 연결 성공', frame);
                    // 서버의 메시지 전송 endpoint를 구독합니다.
                    // 이런형태를 pub sub 구조라고 합니다.
                    stompClient.subscribe("/send", res => {
                        console.log('구독으로 받은 메시지 입니다.', res.body);

                        // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                        recvList.value.push(JSON.parse(res.body))
                    });
                },
                error => {
                    // 소켓 연결 실패
                    console.log('소켓 연결 실패', error);
                    this.connected = false;
                }
            );        
        }

        connect();

        return {
            // variable
            userName,
            message,
            recvList,
            
            // function
            sendMessage,
            send,
            
            connect
        }
    },
}
</script>