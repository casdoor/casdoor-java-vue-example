<!--Copyright 2022 The Casdoor Authors. All Rights Reserved.-->

<!--Licensed under the Apache License, Version 2.0 (the "License");-->
<!--you may not use this file except in compliance with the License.-->
<!--You may obtain a copy of the License at-->

<!--     http://www.apache.org/licenses/LICENSE-2.0 -->

<!--Unless required by applicable law or agreed to in writing, software-->
<!--distributed under the License is distributed on an "AS IS" BASIS,-->
<!--WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.-->
<!--See the License for the specific language governing permissions and-->
<!--limitations under the License.-->

<template>
  <h1>Callback</h1>
</template>

<script setup>
import { onMounted } from 'vue'
import { useCasdoor } from 'casdoor-vue-sdk'
import * as config from '@/config'

const casdoor = useCasdoor()

function login() {
  // checks the state, then posts the code to the backend: POST /api/signin
  casdoor.signin(config.serverUrl).then((res) => {
    if (inIframe()) {
      // the silent sign-in iframe reports the result to the page
      const message = {tag: "Casdoor", type: "SilentSignin", data: res.status === 'ok' ? "success" : "login-failure"};
      window.parent.postMessage(message, window.location.origin);
      return
    }
    if (res.status === 'ok') {
      window.location.href = '/home'
    } else {
      alert(`Login failed: ${res.msg}`)
      window.location.href = '/login'
    }
  })
}

function inIframe() {
  try {
    return window !== window.parent;
  } catch (e) {
    return true;
  }
}

onMounted(() => {
  login()
})
</script>


<style scoped></style>
