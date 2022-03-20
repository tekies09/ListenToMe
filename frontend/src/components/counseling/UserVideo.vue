<template>
	<div v-if="streamManager" id="user-video" :class="{'part-video-hor': !$store.state.isData, 'part-video-ver': $store.state.isData}">
		<p v-if="!$store.state.isData" id="sticker" class="mb-0 f-normal">{{ clientData }}</p>
		<ov-video :stream-manager="streamManager"/>
	</div>
</template>

<script>
import OvVideo from './OvVideo'
import * as faceapi from 'face-api.js'

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData()
			return clientData;
		},
    video: function () {
      return document.querySelector('#user-video:nth-of-type(2) > video')  // 내담자 비디오 영역 선택
    },
	},	

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream
			return JSON.parse(connection.data)
		},
		showSticker: function () {
      Promise.all([
        faceapi.nets.tinyFaceDetector.loadFromUri('/assets/faceapi/models'),
        faceapi.nets.faceLandmark68Net.loadFromUri('/assets/faceapi/models'),
      ])
        .then(
          console.log('ready')
        )
        .catch(errors => {
          console.log(errors)  // 모델을 로딩하는 과정에서 문제가 발생하면 에러 출력
        })

				const canvas = faceapi.createCanvasFromMedia(this.video)
				canvas.setAttribute('class', 'position-absolute')
				const canvasArea = document.querySelector('#user-video')
				canvasArea.append(canvas)

				const videoWidth = document.body.clientWidth * 0.35
				const videoHeight = videoWidth * 3 / 4
				const displaySize = { width: videoWidth, height: videoHeight }
				faceapi.matchDimensions(canvas, displaySize)

				setInterval(async () => {
					const detections = await faceapi.detectAllFaces(
						this.video,
						new faceapi.TinyFaceDetectorOptions()
					)
					.withFaceLandmarks()	

					canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height)
					
					const width = detections[0].landmarks.imageWidth * 0.8
					const height = detections[0].landmarks.imageHeight * 0.8
					const pointX = detections[0].detection.box.x - width * 1.2
					const pointY = detections[0].detection.box.y - height / 1.

					const sticker = new Image()
					sticker.src = '/assets/images/counselor.png'
					faceapi.getContext2dOrThrow(canvas).drawImage(sticker, pointX, pointY, width, height)
				}, 3)
    },
	},

	created () {
		if (this.$store.state.isSticker) {
			setTimeout(async () => {
				this.showSticker()
				console.log('sticker on')
			}, 3000)
		}
	},
}
</script>
