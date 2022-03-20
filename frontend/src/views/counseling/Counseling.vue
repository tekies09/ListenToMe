<template>
  <div id="counseling">
    <!-- body -->
    <section class="d-flex">
			<!-- body: streaming -->
      <div
				:class="{'col-7': $store.state.isData, 'col-9': !$store.state.isData}"
				class="d-flex justify-content-center align-items-center"
				style="background: black;"
			>
				<div id="video-container" :class="{'d-flex': !$store.state.isData}">
					<user-video :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
					<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
				</div>
      </div>

			<!-- message: data -->
			<div v-show="$store.state.isData" :class="{'col-2': $store.state.isData}" class="px-3 area-msg f-normal">
				<!-- header: title -->
				<div class="d-flex align-items-center py-3" style="height: 10vh;">
					<p class="mb-0 f-subtitle">감정 분석</p>
				</div>

				<!-- body: data -->
				<section class="d-flex flex-column justify-content-between px-4 py-3 part-tool" style="height: 70vh;">
					<div class="py-3">
						<div class="d-flex justify-content-between mb-2">
							<p class="mb-0 f-noraml">공포:</p>
							<p class="mb-0 f-noraml">{{ emotionData.fearful }}</p>
						</div>
						<div class="d-flex justify-content-between mb-2">
							<p class="mb-0 f-noraml">기쁨:</p>
							<p class="mb-0 f-noraml">{{ emotionData.happy }}</p>
						</div>
						<div class="d-flex justify-content-between mb-2">
							<p class="mb-0 f-noraml">놀람:</p>
							<p class="mb-0 f-noraml">{{ emotionData.surprised }}</p>
						</div>
						<div class="d-flex justify-content-between mb-2">
							<p class="mb-0 f-noraml">분노:</p>
							<p class="mb-0 f-noraml">{{ emotionData.angry }}</p>
						</div>
						<div class="d-flex justify-content-between mb-2">
							<p class="mb-0 f-noraml">슬픔:</p>
							<p class="mb-0 f-noraml">{{ emotionData.sad }}</p>
						</div>
						<div class="d-flex justify-content-between mb-2">
							<p class="mb-0 f-noraml">역겨움:</p>
							<p class="mb-0 f-noraml">{{ emotionData.disgusted }}</p>
						</div>
						<div class="d-flex justify-content-between mb-2">
							<p class="mb-0 f-noraml">중립:</p>
							<p class="mb-0 f-noraml">{{ emotionData.neutral }}</p>
						</div>
					</div>

					<footer class="d-flex justify-content-center">
						<!-- button: again -->
						<button @click="closeData" class="btn-cancel f-btn" style="width: 4vw">닫기</button>
					</footer>
				</section>
			</div>

      <!-- body: tool -->
      <div class="col-3">
        <!-- tool: memo -->
        <memo v-if="$store.state.isMemo"></memo>

        <!-- tool: records -->
        <records v-else></records>
      </div>
    </section>

		<!-- footer -->
		<footer class="d-flex align-items-center area-footer">
			<div class="col-4"></div>

			<div class="col-4 d-flex justify-content-center">
				<!-- button: 종료 -->
				<button	@click="closeCounseling" class="btn-cancel f-btn">종료</button>
			</div>

			<div class="col-4 d-flex justify-content-end">
				<!-- button: 메모 -->
				<button @click="toggleMemo" class="me-3 btn-tool f-btn">메모</button>

				<!-- button: 기록 -->
				<button @click="toggleRecords" class="me-3 btn-tool f-btn">기록</button>
			</div>
		</footer>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/index.js'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from '@/components/counseling/UserVideo.vue'
import { mapGetters } from 'vuex'

import Memo from '@/components/counseling/Memo.vue'
import Records from '@/components/counseling/Records.vue'

axios.defaults.headers.post['Content-Type'] = 'application/json'

const OPENVIDU_SERVER_URL = "https://i6a605.p.ssafy.io:4431"
const OPENVIDU_SERVER_SECRET = "ssafy605"

export default {
  name: 'Counseling',

  components: {
    UserVideo,
    Memo,
    Records,
  },

  data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],

			mySessionId: 'SessionA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),
		}
	},

  computed: {
    ...mapGetters([ 
			'GE_USERSESSION',
			'GE_USERID',
			// { myName: 'getFirstItem' }, <-- getFirstItem 를 myName 으로 매핑
    ]),

		emotionData: function () {
			return this.$store.state.emotionData
		}
  },

  methods: {
		// for toggling
    toggleMemo: function () {
      this.$store.dispatch('toggleMemo')
    },
    toggleRecords: function () {
			this.$store.dispatch('closeData')
      this.$store.dispatch('toggleRecords')
    },
		closeData: function () {
			this.$store.dispatch('closeData')
		},

    closeCounseling: function () {
    if (this.session) this.session.disconnect()

		this.session = undefined
		this.mainStreamManager = undefined
		this.publisher = undefined
		this.subscribers = []
		this.OV = undefined

		window.removeEventListener('beforeunload', this.leaveSession)
		this.$store.dispatch('closeSticker')  // 스티커 기능 끄기
		this.$store.dispatch('toggleSideBar')  // side bar 토글링
		this.$store.dispatch('closeData')  // 감정분석 영역 닫기
		this.$store.dispatch('toggleMemo')  // 메모 영역 토글링
		
		// 상담 종료 처리
		axios({
			method: 'get',
			url: SERVER.URL + '/schedule-api/end/' + `${this.mySessionId}`,
			headers: {
				'Content-Type': 'application/json',
				'access-token': `${this.$store.state.authToken}`
			},
		})
			.then(res => {
				console.log(res)
			})

		if (this.$store.state.loginState === 1) {
			this.$router.push({name: 'CounselingFeedback'})
		} else if (this.$store.state.loginState === 2) {
			this.$router.push({name: 'Home'})
		}
    },

    joinSession () {
			console.log(this.mySessionId+" ddddd");
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu()

			// --- Init a session ---
			this.session = this.OV.initSession()
			// --- Specify the actions when events take place in the session ---
			
			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream)
				this.subscribers.push(subscriber)
			})

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0)
				if (index >= 0) {
					this.subscribers.splice(index, 1)
				}
			})

			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception)
			})

			// --- Connect to the session with a valid user token ---

			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

						// --- Get your own camera stream with the desired properties ---
						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '1280x720',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
						})

						this.mainStreamManager = publisher
						this.publisher = publisher

						// --- Publish your stream ---
						this.session.publish(this.publisher)
					})
					.catch(error => {
						console.log('z1There was an error connecting to the session:', error.code, error.message);
					})
			})

			window.addEventListener('beforeunload', this.leaveSession)
		},
		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect()

			this.session = undefined
			this.mainStreamManager = undefined
			this.publisher = undefined
			this.subscribers = []
			this.OV = undefined

			window.removeEventListener('beforeunload', this.leaveSession)
		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return
			this.mainStreamManager = stream
		},

		/*
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */

		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							console.warn(`z2 No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							resolve(sessionId)
						} else {
							console.warn(`z3 No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`)
							}
							reject(error.response)
						}
					})
			})
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response))
			})
		},
  },

  created(){
		this.mySessionId = this.GE_USERSESSION
		this.myUserName =  this.GE_USERID
		this.joinSession()
  },
}
</script>
