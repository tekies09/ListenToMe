import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import SERVER from '@/api/index.js'
import router from '@/router/index.js'
import createPersistedState from "vuex-persistedstate"

Vue.use(Vuex)

export default new Vuex.Store({  
  state: {
    // 로그인 상태: { 0: 비로그인 상태, 1: 내담자, 2: 상담사 }
    loginState: 0,
    ////////////////////////////////////////////////////////////////////////////////


    // for toggling
    isNavbar: false,
    isSideBar: true,  // 상담실 입장 여부에 따른 왼쪽 사이드바 영역 토클링
    isMemo: true,  // 상담실 내 메모와 기록 도구 영역 토글링
    isData: false,  // 감정분석 영역 토글링
    isSticker: false,  // 스티커 기능 온오프
    navbarState: true,
    ////////////////////////////////////////////////////////////////////////////////


    // 감정분석 데이터
    emotionData: '',
    ////////////////////////////////////////////////////////////////////////////////

    
    authToken: localStorage.getItem('jwt'),
    userid: "",
    usersession: "",

    // 로그인한 유저의 이메일
    userEmail: '',

    // 카운슬러 디테일 페이지
    counselorDetail: [],

    counsState: '',

    // 회원가입 후 사진 등록 
    myPhoto: '',
  },

  getters: {
    isLoggedIn: function (state) {
      return state.authToken ? true : false
    },

    GE_USERID: (state) => {
      return state.userid
    },
    GE_USERSESSION: (state) => {
      return state.usersession
    },
  },

  mutations: {
    // accounts
    SET_TOKEN: function (state, token) { 
      state.authToken = token
      state.isLoggedIn = true
      localStorage.setItem('jwt', token)
    },

    REMOVE_TOKEN: function (state) {
      localStorage.removeItem('jwt')
      state.authToken = ''
      state.isLoggedIn = false
    },

    SE_LOGINSTATE: function (state, payload) {
      state.loginState = payload
    },
    ////////////////////////////////////////////////////////////////////////////////


    // for toggling
    ON_NAVBAR: function (state) {
      state.isNavbar = true
    },
    OFF_NAVBAR: function (state) {
      state.isNavbar = false
    },
    TOGGLE_SIDEBAR: function (state) {
      state.isSideBar = !state.isSideBar
    },
    TOGGLE_MEMO: function (state) {
      state.isMemo = true
    },
    TOGGLE_RECORDS: function (state) {
      state.isMemo = false
    },
    TOGGLE_DATA: function (state) {
      state.isData = !state.isData
    },
    CLOSE_DATA: function (state) {
      state.isData = false
    },
    TOGGLE_STICKER: function (state) {
      state.isSticker = !state.isSticker
    },
    CLOSE_STICKER: function (state) {
      state.isSticker = false
    },
    ////////////////////////////////////////////////////////////////////////////////


    // 감정분석 데이터
    SAVE_DATA: function (state, emotionData) {
      state.emotionData = emotionData
    },
    ////////////////////////////////////////////////////////////////////////////////

    // 리스너에서 클릭한 상담사 정보 관련
    LOAD_COUNSELOR_PROFILE: function (state, results) {
      state.counselorDetail = results
      console.log(results)
    },


    SE_USERID: function (state, payload) {
      state.userid = payload
    },
    SE_USERSESSION: function (state, payload) {
      state.usersession = payload
    },

    GET_USER_EMAIL: function (state, results) {
      state.userEmail = results
    },

    CHECK_COUNSELING_STATE: function (state, counselingState) {
      state.counsState = counselingState
    },
  },

  actions: {
    // accounts
    Signup: function (context, credentials) {
      axios({
        url: SERVER.URL + SERVER.ROUTES.signup,
        method: 'post',
        data: credentials,
      })
      .then(() => {
        router.push({name: 'LoginForClient'})
      })
      .catch((err) => {
        console.log(err)
      })
    },

    SignupForCounselor: function (context, credentials) {
      axios({
        url: SERVER.URL + SERVER.ROUTES.co_signup,
        method: 'post',
        data: credentials,
      })
      .then(() => {
        router.push({name: 'LoginForCounselor'})
      })
      .catch((err) => {
        console.log(err)
      })
    },

    Login: function ({ commit }, credentials) {
      commit('GET_USER_EMAIL', credentials.email)
      if (this.getters.isLoggedIn) {
        router.push('/')
      }
      else {
        axios({
          url: SERVER.URL + SERVER.ROUTES.login,
          method: 'post',
          data: credentials,
        })
        .then((res) => {
          commit('SET_TOKEN', res.data['access-token'])
          commit('SE_LOGINSTATE', 1)
          router.push('/')
          console.log(this.state.userEmail)
        })
        .catch((err) => {
          console.log(err)
        })
      }
    },

    LoginForCounselor: function ({ commit }, credentials) {
      commit('GET_USER_EMAIL', credentials.email)
      if (this.getters.isLoggedIn) {
        router.push('/')
      }
      else {
        axios({
          url: SERVER.URL + SERVER.ROUTES.co_login,
          method: 'post',
          data: credentials,
        })
        .then((res) => {
          console.log(this.state.myPhoto)
          Promise.all([
            commit('SET_TOKEN', res.data['access-token']),
            commit('SE_LOGINSTATE', 2)
          ])
          .then(() => {
            const formdata = new FormData();
            formdata.append('multipartFile', this.state.myPhoto);
            axios({
              method: 'post',
              url: SERVER.URL + '/counselor-api/user/image',
              data: formdata,
              headers: {
                'access-token': `${this.state.authToken}`,
                'Content-Type': 'multipart/form-data',
              }
            })
            .then(() => {
              console.log('사진등록')
            })
            .catch((err) => console.log(err));
          })
          router.push('/')
          console.log(this.state.userEmail)
        })
        .catch((err) => {
          console.log(err)
        })
      }
    },

    Logout: function ({ commit }) {
      commit('REMOVE_TOKEN')
      commit('SE_LOGINSTATE', 0)
      router.push('/')
        .catch(() => {})
    },
    ////////////////////////////////////////////////////////////////////////////////////////////


    // for toggling
    onNavbar: function ({ commit }) {
      commit('ON_NAVBAR')
    },
    offNavbar: function ({ commit }) {
      commit('OFF_NAVBAR')
    },
    toggleSideBar: function ({ commit }) {
      commit('TOGGLE_SIDEBAR')
    },
    toggleMemo: function ({ commit }) {
      commit('TOGGLE_MEMO')
    },
    toggleRecords: function ({ commit }) {
      commit('TOGGLE_RECORDS')
    },
    toggleData: function ({ commit }) {
      commit('TOGGLE_DATA')
    },
    closeData: function ({ commit }) {
      commit('CLOSE_DATA')
    },
    toggleSticker: function ({ commit }) {
      commit('TOGGLE_STICKER')
    },
    closeSticker: function ({ commit }) {
      commit('CLOSE_STICKER')
    },
    ////////////////////////////////////////////////////////////////////////////////


    // 감정분석 데이터
    saveEmotionData: function ({ commit }, emotionData) {
      commit('SAVE_DATA', emotionData)
    },
    ////////////////////////////////////////////////////////////////////////////////

    SE_USERID: (context, payload) => {
      return context.commit('SE_USERID', payload)
    },
    SE_USERSESSION: (context, payload) => {
      return context.commit('SE_USERSESSION', payload)
    },

    // move up and down
    moveUp: () => {
      window.scrollTo(0,0)
      document.body.classList.remove('overflow-hidden')
    },

    moveDown: () => {
      let bottomLocation = document.documentElement.scrollHeight
      document.body.classList.add('overflow-hidden')
      window.scrollTo(0, bottomLocation)
    },


    // 리스너 각 페이지로 이동
    LoadCounselorProfile: function ({ commit }, request) {
      axios({
        method: 'get',
        url: SERVER.URL + `/counselor-api/user/${request.email}`, 
      })
      .then((res) => {
        commit('LOAD_COUNSELOR_PROFILE', res.data)
        // console.log(res)
        router.push({name: 'CounselorDetail', params: {coEmail : request.email}})
      })
      .catch((err) => {
        console.log(err)
      })
    },

    checkCounselingState: function ({ commit }, counselingState) {
      commit('CHECK_COUNSELING_STATE', counselingState)
    },
  },

  plugins: [createPersistedState()],
})
