<template>
  <div id="myschedule" class="d-flex flex-column align-items-center pt-5 mx-5">
    <div class="mb-4 p-4 part-profile">
      <div>
        <h1 class="px-4 py-1 fw-bold">스케줄 관리</h1>
      </div>
      <hr>
      <div>
        <table border="1" align="center" class="table wrap">
          <thead>
            <tr>
              <th></th>
              <th v-for="date in dateList" :key="date">
                {{ date }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>09:00</th>
              <td v-for="(time, i) in firsttime" :key="i">
                <div v-if="time[3] === 1">
                  <button class="schedule-btn2 fw-bold" disabled>미신청</button>
                </div>
                <div v-else-if="time[3] === 2">
                  <button 
                    @click="modalViewed= 1, modalContent= time, scheduleId= time[0], position= firsttime, index= i, getClientInfo()"
                    class="schedule-btn1 fw-bold">승인대기</button>
                </div>
                <div v-else-if="time[3] === 3">
                  <button
                    @click="modalViewed= 1, modalContent= time, scheduleId= time[0], position= firsttime, index= i, check= false, getClientInfo()" 
                    class="schedule-btn3 fw-bold">예약완료</button>
                </div>
                <div v-else>
                  <button 
                    @click="modalViewed= 2, position= firsttime, index= i, getFormatDate()"
                    class="schedule-btn4 fw-bold">상담열기</button>
                </div>
              </td>
            </tr>
            <tr>
              <th>11:00</th>
              <td v-for="(time, j) in secondtime" :key="j">
                <div v-if="time[3] === 1">
                  <button class="schedule-btn2 fw-bold" disabled>미신청</button>
                </div>
                <div v-else-if="time[3] === 2">
                  <button 
                    @click="modalViewed= 1, modalContent= time, scheduleId= time[0], position= secondtime, index= j, getClientInfo()"
                    class="schedule-btn1 fw-bold">승인대기</button>
                </div>
                <div v-else-if="time[3] === 3">
                  <button
                    @click="modalViewed= 1, modalContent= time, scheduleId= time[0], position= secondtime, index= j, check= false, getClientInfo()" 
                    class="schedule-btn3 fw-bold">예약완료</button>
                </div>
                <div v-else>
                  <button
                    @click="modalViewed= 2, position= secondtime, index= j, getFormatDate()"
                    class="schedule-btn4 fw-bold">상담열기</button>
                </div>
              </td>
            </tr>
            <tr>
              <th>15:00</th>
              <td v-for="(time, s) in thirdtime" :key="s">
                <div v-if="time[3] === 1">
                  <button class="schedule-btn2 fw-bold" disabled>미신청</button>
                </div>
                <div v-else-if="time[3] === 2">
                  <button 
                    @click="modalViewed= 1, modalContent= time, scheduleId= time[0], position= thirdtime, index= s, getClientInfo()"
                    class="schedule-btn1 fw-bold">승인대기</button>
                </div>
                <div v-else-if="time[3] === 3">
                  <button 
                    @click="modalViewed= 1, modalContent= time, scheduleId= time[0], position= thirdtime, index= s, check= false, getClientInfo()"
                    class="schedule-btn3 fw-bold">예약완료</button>
                </div>
                <div v-else>
                  <button 
                    @click="modalViewed= 2, position= thirdtime, index= s, getFormatDate()"
                    class="schedule-btn4 fw-bold">상담열기</button>
                </div>
              </td>
            </tr>
            <tr>
              <th>17:00</th>
              <td v-for="(time, k) in fourthtime" :key="k">
                <div v-if="time[3] === 1">
                  <button class="schedule-btn2 fw-bold" disabled>미신청</button>
                </div>
                <div v-else-if="time[3] === 2">
                  <button 
                    @click="modalViewed= 1, modalContent= time, scheduleId= time[0], position= fourthtime, index= k, getClientInfo()"
                    class="schedule-btn1 fw-bold">승인대기</button>
                </div>
                <div v-else-if="time[3] === 3">
                  <button 
                    @click="modalViewed= 1, modalContent= time, scheduleId= time[0], position= fourthtime, index= k, check= false, getClientInfo()"
                    class="schedule-btn3 fw-bold">예약완료</button>
                </div>
                <div v-else>
                  <button 
                    @click="modalViewed= 2, position= fourthtime, index= k, getFormatDate()"
                    class="schedule-btn4 fw-bold">상담열기</button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-if="modalViewed === 1">
        <div class="background">
          <div class="window">
            <div class="popup">
              <div class="modal-header d-flex align-items-center justify-content-center pb-2">
                <div v-if="check">
                  <p>접수 확인</p>
                </div>
                <div v-else> 
                  <p>예약 정보 확인</p>
                </div>
              </div>
              <div class="modal-body px-5 mx-3">
                <p>상담 일자 : {{ modalContent[1] }}</p>
                <p>시작 시간 : {{ modalContent[2] }}</p>
                <p>예약자 : {{ clientName }}</p>
                <p>상담사 : {{ name }}</p>
                <div v-if="sticker">
                  <p>스티커 사용 O</p>
                </div>
                <div v-else>
                  <p>스티커 사용 X</p>
                </div>
              </div>
              <div class="modal-footer">
                <div v-show="check">
                  <button class="modal-button px-3 py-1" style="background: rgba(86, 160, 255, 0.93);" @click="acceptConsultation">승인</button>
                </div>
                <button class="modal-button px-3 py-1" style="background: rgba(255, 94, 94, 0.92);" @click="modalViewed= 0">닫기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-if="modalViewed === 2">
        <div class="background">
          <div class="window">
            <div class="popup">
              <div class="modal-header d-flex align-items-center justify-content-center pb-2">
                <p>상담 개설</p>
              </div>
              <div class="modal-body p-4 my-5">
                <p>상담 일자 : {{ reserveDate }}</p>
                <p>시작 시간 : {{ reserveTime }}</p>
              </div>
              <div class="modal-footer m-modal-footer">
                <button class="modal-button px-3 py-1" style="background: rgba(86, 160, 255, 0.93);" @click="openConsultation">확인</button>
                <button class="modal-button px-3 py-1" style="background: rgba(255, 94, 94, 0.92);" @click="modalViewed= 0">닫기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>  
  </div>
</template>

<script>
import axios from "axios";
import SERVER from "@/api/index.js";
import moment from 'moment'
moment.locale('ko');

export default {
  name: 'Schedule',
  data: function(){
    return {
      dateList: [],
      formatDateList: [],
      weekNumber: 7,
      hourlyWorkList: [],

      scheduleList: [],
      firsttime: [],
      secondtime: [],
      thirdtime: [],
      fourthtime: [],

      // counselor 정보 가져오기
      name: '',

      // modal
      modalViewed: 0,
      modalContent: [],

      // 상담 승인 시 전달 내용
      scheduleId: 0,

      // 예약자 정보
      clientName: '',
      sticker: '',
      
      // 예약 위치 확인용 + 상담 등록 시 필요한 날짜와 시간 위치 확인용
      position: [],
      index: 0,

      // 상담 등록 시 보낼 데이터
      reserveTime: '',
      reserveDate: '',

      // 승인 대기면 true 예약완료면 false
      check: true
    }
  },
  methods: {
    // 상담 등록 시 필요한 날짜, 시간 포맷
    getFormatDate() {
      var month = this.dateList[this.index].substring(0, 2);
      var day = this.dateList[this.index].substring(3, 5);
      this.reserveDate = '2022'.concat('-', month, '-', day);
     
      if (this.position === this.firsttime) {
        this.reserveTime = "09:00";
      }
      else if (this.position === this.secondtime) {
        this.reserveTime = "11:00";
      }
      else if (this.position === this.thirdtime) {
        this.reserveTime = "15:00";
      }
      else if (this.position === this.fourthtime) {
        this.reserveTime = "17:00";
      }
    },

    // 상담 시간 등록
    openConsultation() {
      var openDateTime = this.reserveDate.concat('T', this.reserveTime, ':', '00');
      axios({
        method: 'post',
        url: SERVER.URL + '/schedule-api/time',
        headers: {
          'Content-Type': 'application/json;',
          'access-token': `${this.$store.state.authToken}`
        },
        data: {
          dateTime: openDateTime
        }
      })
      .then(() => {
        console.log('상담 시간 등록 완료')
        for ( var i=0; i<this.scheduleList.length; i++ ) {
          var contime = this.scheduleList[i].dateTime;
          if (this.dateList[this.index] === contime) {
            this.scheduleList[i].state = 0;
          }
        }
        this.position[this.index][3] = 1;
      })
      .catch((err) => console.log(err));
      this.modalViewed = 0;
      //this.$router.push('Profile')
      // window.location.reload();
      // alert("등록이 완료되었습니다.")
    },

    // 상담사 정보 조회
    getCounselorData() {
      axios({
        method: 'get',
        url: SERVER.URL + `/counselor-api/user/${this.$store.state.userEmail}`,
      })
      .then((res) => {
        // console.log(res)
        this.name = res.data.userInfo.name;
      })
      .catch((err) => console.log(err));
    },

    // 상담 스케줄 조회
    getCounselorSchedule() {
      axios({
        method: 'get',
        url: SERVER.URL + `/schedule-api/schedule/${this.$store.state.userEmail}`
      })
      .then((res) => {
        this.scheduleList = res.data;
        // console.log(this.scheduleList)
        this.filterDate(this.scheduleList)
      })
      .catch((err) => console.log(err));
    },

    // 예약된 상담 정보 조회
    getClientInfo() {
      axios({
        method: 'get',
        url: SERVER.URL + `/schedule-api/${this.scheduleId}`
      })
      .then((res) => {
        console.log(res.data)
        this.clientName = res.data.client_name;
        this.sticker = res.data.sticker;
      })
      .catch((err) => console.log(err));
    },

    // 오늘부터 일주일간 날짜 받기
    setDateList() {
      var dateListClone = this.dateList;
      dateListClone = []
      var date = moment(this.startDate);
      dateListClone.push(date.format('MM/DD'));
      for ( var i = 0; i < (this.weekNumber - 1); i++ ) {
        dateListClone.push(date.add(1, 'day').format('MM/DD'));
      }
      this.dateList = dateListClone;
    },

    // 날짜와 시간에 따라 필터링
    filterDate() {
      // [id, date, time, 0 or 1] 로 저장
      // 0: 상담시간 x / 1: 미신청 / 2: 승인 대기 / 3: 예약완료
      var first = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]];
      var second = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]];
      var third = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]];
      var fourth = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]];

      for ( var i=0; i<this.scheduleList.length; i++ ) {
        this.scheduleList[i].dateTime = this.formatDate(this.scheduleList[i].dateTime);
        var k = this.scheduleList[i].dateTime;
        var idx = this.scheduleList[i].id;
        var state = this.scheduleList[i].state;
        var time = k.substring(11, 16);
        var date = k.substring(5, 10);  

        // 미신청 (내담자 입장에서는 신청가능)
        if (state === 0) {
          if (time === "09:00") {
            var day1 = this.dateList.indexOf(date);
            first[day1].splice(3, 1, 1);
            first[day1].splice(2, 1, time);
            first[day1].splice(1, 1, date);
            first[day1].splice(0, 1, idx);
          }
          else if (time === "11:00") {
            var day2 = this.dateList.indexOf(date);
            second[day2].splice(3, 1, 1);
            second[day2].splice(2, 1, time);
            second[day2].splice(1, 1, date);
            second[day2].splice(0, 1, idx);
          }
          else if (time === "15:00") {
            var day3 = this.dateList.indexOf(date);
            third[day3].splice(3, 1, 1);
            third[day3].splice(2, 1, time);
            third[day3].splice(1, 1, date);
            third[day3].splice(0, 1, idx);
          }
          else if (time === "17:00") {
            var day4 = this.dateList.indexOf(date);
            fourth[day4].splice(3, 1, 1);
            fourth[day4].splice(2, 1, time);
            fourth[day4].splice(1, 1, date);
            fourth[day4].splice(0, 1, idx);
          }
        }
        // 상담자 입장에서는 승인 대기 중 (내담자 입장에서는 신청 대기)
        else if (state === 1) {
          if (time === "09:00") {
            var day11 = this.dateList.indexOf(date);
            first[day11].splice(3, 1, 2);
            first[day11].splice(2, 1, time);
            first[day11].splice(1, 1, date);
            first[day11].splice(0, 1, idx);
          }
          else if (time === "11:00") {
            var day22 = this.dateList.indexOf(date);
            second[day22].splice(3, 1, 2);
            second[day22].splice(2, 1, time);
            second[day22].splice(1, 1, date);
            second[day22].splice(0, 1, idx);
          }
          else if (time === "15:00") {
            var day33 = this.dateList.indexOf(date);
            third[day33].splice(3, 1, 2);
            third[day33].splice(2, 1, time);
            third[day33].splice(1, 1, date);
            third[day33].splice(0, 1, idx);
          }
          else if (time === "17:00") {
            var day44 = this.dateList.indexOf(date);
            fourth[day44].splice(3, 1, 2);
            fourth[day44].splice(2, 1, time);
            fourth[day44].splice(1, 1, date);
            fourth[day44].splice(0, 1, idx);
          }
        }
        // 상담자 입장에서는 예약 완료 (내담자 입장에서는 마감)
        else if (state === 2) {
          if (time === "09:00") {
            var day111 = this.dateList.indexOf(date);
            first[day111].splice(3, 1, 3);
            first[day111].splice(2, 1, time);
            first[day111].splice(1, 1, date);
            first[day111].splice(0, 1, idx);
          }
          else if (time === "11:00") {
            var day222 = this.dateList.indexOf(date);
            second[day222].splice(3, 1, 3);
            second[day222].splice(2, 1, time);
            second[day222].splice(1, 1, date);
            second[day222].splice(0, 1, idx);
          }
          else if (time === "15:00") {
            var day333 = this.dateList.indexOf(date);
            third[day333].splice(3, 1, 3);
            third[day333].splice(2, 1, time);
            third[day333].splice(1, 1, date);
            third[day333].splice(0, 1, idx);
          }
          else if (time === "17:00") {
            var day444 = this.dateList.indexOf(date);
            fourth[day444].splice(3, 1, 3);
            fourth[day444].splice(2, 1, time);
            fourth[day444].splice(1, 1, date);
            fourth[day444].splice(0, 1, idx);
          }
        }
      }
      this.firsttime = first;
      this.secondtime = second;
      this.thirdtime = third;
      this.fourthtime = fourth;

      // console.log(this.firsttime)  
      // console.log(this.secondtime)  
      // console.log(this.thirdtime)  
      // console.log(this.fourthtime)  
    },

    // 날짜 형식 포맷
    formatDate(date, format = 'YYYY/MM/DD HH:mm') {
      return moment(new Date(date)).format(format);
    }, 

    // 상담자가 신청한 상담 승인
    acceptConsultation() {
      axios({
        method: 'get',
        url: SERVER.URL + `/schedule-api/accept/${this.scheduleId}`,
        headers: {
          'access-token': `${this.$store.state.authToken}`
        },
      })
      .then(() => {
        console.log('승인 ok')
        for (var i=0; i<this.scheduleList.length; i++ ) {
          var idx = this.scheduleList[i].id;
          if (this.scheduleId === idx) {
            this.scheduleList[i].state = 2;
          }
        }
        this.position[this.index][3] = 3;
      })
      .catch((err) => console.log(err));
      this.modalViewed = 0;
      console.log(this.position)
      this.$router.push({name: 'ListFromCounselor'})
      // alert("승인이 완료되었습니다.")
    }
  },
  computed: {
    startDate: {
      get() {
        return moment()
      },
      set(date) {
        this.setDateList(date)
      }
    },
  },
  mounted() {
    this.setDateList(this.startDate)    
    this.getCounselorData()
    this.getCounselorSchedule()
  },
}

</script>

<style>
#myschedule {
  width: 100%;
  color: #2c3e50;
  margin-top: 60px;
}

#myschedule h1{
  font-size: 36px;
  margin: 20px 0px;
  color: #695D5D;
}

#myschedule .schedule-btn1 {
  width: 100%;
  font-size: 20px;
  padding: 5px;
  background: #64C7FF;
  border-radius: 30px;
  border: none;
}

#myschedule .schedule-btn2 {
  width: 100%;
  font-size: 20px;
  padding: 5px;
  background: #D9D9D9;
  border-radius: 30px;
  border: none;
}

#myschedule .schedule-btn3 {
  width: 100%;
  font-size: 20px;
  padding: 5px;
  background: #FF9999;
  border-radius: 30px;
  border: none;
}

#myschedule .schedule-btn4 {
  width: 100%;
  font-size: 20px;
  padding: 5px;
  background: #ffdab3;
  opacity: 0.6;
  border-radius: 30px;
  border: none;
}

#myschedule table {
  width: 100%;
  display: table;
  table-layout: fixed;
  font-size: 20px;
  background-color: rgb(255, 255, 255);
  opacity: 0.8;
  border-collapse: collapse;
  text-align: center;
}

#myschedule th, td {
  border: 1px solid #e0e0e0;
  font-weight: bold;
}

.background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border-radius: 20px;
  background-color: rgba(0, 0, 0, 0.3);
  z-index: 1000;
}

.show {
  opacity: 1;
  z-index: 1000;
  transition: all 0.5s;
}

.window {
  position: relative;
  width: 100%;
  height: 100%;
}

.popup {
  position: absolute;
  top: 50%;
  left: 50%;
  border-radius: 10px;
  transform: translate(-50%, -50%);
  background-color: #ffffff;
  box-shadow: 0 2px 7px rgba(0, 0, 0, 0.3);
}

.show .popup {
  transform: translate(-50%, -50%);
  transition: all 0.5s;
}

.modal-header {
  font-size: 32px;
  font-weight: bold;
  border-radius: 5px;
  background-color: #fde6cd;
}

.modal-body {
  text-align: center;
  font-size: 24px;
}

.modal-button {
  border-radius: 30px;
  border: none;
  font-size: 20px;
  margin: 0 auto;
}
</style>