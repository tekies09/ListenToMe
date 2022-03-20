<template>
  <div id="memo" class="px-3">
    <!-- header -->
    <div class="d-flex justify-content-between align-items-center py-3" style="height: 10vh;">
      <!-- title -->
      <p class="mb-0 f-subtitle">메모</p>

      <!-- message: 저장 여부 -->
      <p v-show="active === 1" class="mb-0 pe-3 f-normal">분석 시작</p>
      <p v-show="active === 2" class="mb-0 pe-3 f-normal">저장 완료</p>
      <p v-show="active === 3" class="mb-0 pe-3 f-normal">내용을 입력해주세요.</p>
    </div>

    <!-- body: form -->
    <section class="d-flex flex-column justify-content-evenly align-items-center p-3 part-tool" style="height: 70vh;">
      <!-- form: 제목 -->
      <input
        v-model.trim="memo.title"
        type="text" placeholder="제목" class="form-control border-2 f-normal" required
      >

      <!-- form: 내용 -->
      <textarea
        v-model.trim="memo.content"
        placeholder="내용" class="form-control border-2 mb-2 form-content f-normal" required
      >
      </textarea>

      <!-- footer: 기능 버튼 -->
      <footer class="d-flex justify-content-between" style="width: 20vw;">
        <!-- button: 감정 분석 -->
        <div v-if="$store.state.loginState === 2">
          <button @click="toggleData" class="btn-func f-btn" style="width: 8vw;">감정 분석</button>
        </div>

        <!-- button: 저장 -->
        <button @click="postMemo" class="btn-func f-btn">저장</button>
      </footer>
    </section>
  </div>
</template>

<script>
import * as faceapi from 'face-api.js'
import axios from 'axios'
import SERVER from '@/api/index.js'

export default {
  name: 'Memo',

  data: function () {
    return {
      emotionData: {
        angry: 0,
        disgusted: 0,
        fearful: 0,
        happy: 0,
        neutral: 0,
        sad: 0,
        surprised: 0,
      },
      memo: {
        id: '',
        title: '',
        content: ''
      },
      active: 0,  // 메시지 출력 토글링
    }
  },

  computed: {
    video: function () {
      return document.querySelector('#user-video:nth-of-type(2) > video')  // 내담자 비디오 영역 선택
    },
  },

  methods: {
    // emotion recognition
		getData: function () {
      Promise.all([
        faceapi.nets.tinyFaceDetector.loadFromUri('/assets/faceapi/models'),
        faceapi.nets.faceLandmark68Net.loadFromUri('/assets/faceapi/models'),
        faceapi.nets.faceRecognitionNet.loadFromUri('/assets/faceapi//models'),
        faceapi.nets.faceExpressionNet.loadFromUri('/assets/faceapi//models'),
      ])
        .then(
          console.log('ready')  // 사용할 모델이 모두 정상적으로 로딩이 되었다면 감정분석 시작
        )
        .catch(errors => {
          console.log(errors)  // 모델을 로딩하는 과정에서 문제가 발생하면 에러 출력
        })

      setTimeout(async () => {
        const detections = await faceapi.detectAllFaces(
          this.video,
          new faceapi.TinyFaceDetectorOptions()
        )
          .withFaceLandmarks().withFaceExpressions()

        this.emotionData = detections[0].expressions
        for (let key in this.emotionData) {
          const value = this.emotionData[key]
          this.emotionData[key] = Math.round(value * 100000000) / 1000000
        }

        this.$store.dispatch('saveEmotionData', this.emotionData)
      }, 100)
    },

    // for toggling
    changeStatusToAnal: function () {
      this.active = 1
    },
    changeStatusToSave: function () {
      this.active = 2
    },
    changeStatusToError: function () {
      this.active = 3
    },
    changeStatusToHide: function () {
      this.active = 0
    },
    showMessageOfAnal: function () {
      this.changeStatusToAnal()
      setTimeout(this.changeStatusToHide, 500)
    },
    showMessageOfSaving: function () {
      this.changeStatusToSave()
      setTimeout(this.changeStatusToHide, 500)
    },
    showMessageOfError: function () {
      this.changeStatusToError()
      setTimeout(this.changeStatusToHide, 500)
    },
    toggleData: function () {
      this.getData()
      this.showMessageOfAnal()
      // 감정분석 영역이 활성화 되어 있지 않은 경우
      if (!this.$store.state.isData) {
        this.$store.dispatch('toggleData')
      }
    },

    postMemo: function () {
      if (this.memo.title && this.memo.content) {
        this.memo.id = this.$store.state.usersession
        axios({
          method: 'post',
          url: SERVER.URL + SERVER.ROUTES.memoCreation,
          headers: {
            'Content-Type': 'application/json',
            'access-token': `${this.$store.state.authToken}`
          },
          data: this.memo,
        })
          .then(res => {
            console.log(res)
            this.memoSaved = this.memo
            this.showMessageOfSaving()
          })
      } else {
        this.showMessageOfError()
      }
    },
  },
}
</script>
