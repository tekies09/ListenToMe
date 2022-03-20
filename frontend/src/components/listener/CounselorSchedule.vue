<template>
  <div id="counselorschedule" class="d-flex flex-column align-items-center">
    <p class="mt-4 col-12 ps-2 f-subtitle">일정</p>
    <div>
      <table border="1" align="center" class="table wrap">
        <thead >
          <tr>
            <th></th>
            <th v-for="date in dateList" :key="date">
              {{ date }}
            </th>
          </tr>
        </thead>
        <tbody >
          <tr>
            <th>09:00</th>
            <td v-for="(time, i) in firsttime" :key="i">
              <div v-if="time[3] === 1">
                <button 
                  @click="modalViewed= 1, modalContent= time, reserveInfo.scheduleId= time[0], position= firsttime, index= i" 
                  class="schedule-btn1 fw-bold">
                  신청가능
                </button>
              </div>
              <div v-else-if="time[3] === 2">
                <button class="schedule-btn2 fw-bold" disabled>신청대기</button>
              </div>
              <div v-else-if="time[3] === 3">
                <button class="schedule-btn3 fw-bold" disabled>마감</button>
              </div>
            </td>
          </tr>
          <tr>
            <th>11:00</th>
            <td v-for="(time, j) in secondtime" :key="j">
              <div v-if="time[3] === 1">
                <button 
                  @click="modalViewed= 1, modalContent= time, reserveInfo.scheduleId= time[0], position= secondtime, index= j" 
                  class="schedule-btn1 fw-bold">
                  신청가능
                </button>
              </div>
              <div v-else-if="time[3] === 2">
                <button class="schedule-btn2 fw-bold" disabled>신청대기</button>
              </div>
              <div v-else-if="time[3] === 3">
                <button class="schedule-btn3 fw-bold" disabled>마감</button>
              </div>
            </td>
          </tr>
          <tr>
            <th>15:00</th>
            <td v-for="(time, s) in thirdtime" :key="s">
              <div v-if="time[3] === 1">
                <button 
                  @click="modalViewed= 1, modalContent= time, reserveInfo.scheduleId= time[0], position= thirdtime, index= s" 
                  class="schedule-btn1 fw-bold">
                  신청가능
                </button>
              </div>
              <div v-else-if="time[3] === 2">
                <button class="schedule-btn2 fw-bold" disabled>신청대기</button>
              </div>
              <div v-else-if="time[3] === 3">
                <button class="schedule-btn3 fw-bold" disabled>마감</button>
              </div>
            </td>
          </tr>
          <tr>
            <th>17:00</th>
            <td v-for="(time, k) in fourthtime" :key="k">
              <div v-if="time[3] === 1">
                <button 
                  @click="modalViewed= 1, modalContent= time, reserveInfo.scheduleId= time[0], position= fourthtime, index= k" 
                  class="schedule-btn1 fw-bold">
                  신청가능
                </button>
              </div>
              <div v-else-if="time[3] === 2">
                <button class="schedule-btn2 fw-bold" disabled>신청대기</button>
              </div>
              <div v-else-if="time[3] === 3">
                <button class="schedule-btn3 fw-bold" disabled>마감</button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="modalViewed">
      <div class="background">
        <div class="window">
          <div class="popup">
            <div class="modal-header d-flex align-items-center justify-content-center pb-2">
              <p>상담 신청</p>
            </div>
            <div class="modal-body px-3 m-4">
              <p>상담 일자 : {{ modalContent[1] }}</p>
              <p>시작 시간 : {{ modalContent[2] }}</p>
              <label>
                <input type="checkbox" id="sticker" v-model="reserveInfo.isSticker">  상담 시 스티커 사용
              </label>
            </div>
            <div class="modal-footer">
              <button class="modal-button px-3 py-1" style="background: rgba(86, 160, 255, 0.93);" @click="reserveConsultation">신청</button>
              <button class="modal-button px-3 py-1" style="background: rgba(255, 94, 94, 0.92);" @click="modalViewed= 0">닫기</button>
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
import { mapState } from 'vuex'
import moment from 'moment'
moment.locale('ko');

export default {
  name: 'CounselorSchedule',
  props: {
    coEmail: String
  },
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

      // test 용
      name: '',
      email: '',

      // modal
      modalViewed: 0,
      modalContent: [],

      // 상담 신청 시 전달 내용
      reserveInfo: {
        scheduleId: 0,
        isSticker: false
      },
      
      // 예약 위치 확인 용
      position: [],
      index: 0
    }
  },
  methods: {
    getCounselorData() {
      axios({
        method: 'get',
        url: SERVER.URL + `/counselor-api/user/${this.coEmail}`,
      })
      .then((res) => {
        console.log(res)
        this.name = res.data.userInfo.name
        this.email = res.data.userInfo.email
      })
      .catch((err) => console.log(err));
    },
    getCounselorSchedule() {
      axios({
        method: 'get',
        url: SERVER.URL + `/schedule-api/schedule/${this.coEmail}`
      })
      .then((res) => {
        this.scheduleList = res.data
        // console.log(this.scheduleList)
        this.filterDate(this.scheduleList)
      })
      .catch((err) => console.log(err));
    },
    setDateList() {
      var dateListClone = this.dateList
      dateListClone = []
      var date = moment(this.startDate)
      dateListClone.push(date.format('MM/DD'))
      for ( var i = 0; i < (this.weekNumber - 1); i++ ) {
        dateListClone.push(date.add(1, 'day').format('MM/DD'))
      }
      this.dateList = dateListClone
    },
    filterDate() {
      // [id, date, time, 0 or 1] 로 저장
      // 0: 상담시간 x / 1: 신청 가능 / 2: 신청 대기 / 3: 마감
      var first = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]];
      var second = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]];
      var third = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]];
      var fourth = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]];

      for (var i=0; i<this.scheduleList.length; i++ ) {
        this.scheduleList[i].dateTime = this.formatDate(this.scheduleList[i].dateTime);
        var k = this.scheduleList[i].dateTime;
        var idx = this.scheduleList[i].id
        var state = this.scheduleList[i].state
        var time = k.substring(11, 16);
        var date = k.substring(5, 10);  

        // 신청 가능 상태 (상담자 입장에서는 미신청)
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
        // 신청 대기 상태 (상담자 입장에서는 승인 대기 중)
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
        // 내담자 입장에서는 마감 (상담자 입장에서는 예약 완료)
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
      this.firsttime = first
      this.secondtime = second
      this.thirdtime = third
      this.fourthtime = fourth

      // console.log(this.firsttime)  
      // console.log(this.secondtime)  
      // console.log(this.thirdtime)  
      // console.log(this.fourthtime)  
    },

    formatDate(date, format = 'YYYY/MM/DD HH:mm') {
      return moment(new Date(date)).format(format);
    }, 
    reserveConsultation() {
      console.log(this.reserveInfo)
      if (this.$store.state.loginState) {
        axios({
          method: 'post',
          url: SERVER.URL + '/schedule-api/request',
          headers: {
            'Content-Type': 'application/json;',
            'access-token': `${this.$store.state.authToken}`
          },
          data: this.reserveInfo
        })
          .then(() => {
            console.log('예약 ok')
            for (var i=0; i<this.scheduleList.length; i++ ) {
              var idx = this.scheduleList[i].id
              if (this.reserveInfo.scheduleId === idx) {
                this.scheduleList[i].state = 1;
              }
            }
            this.position[this.index][3] = 2;
          })
          .catch((err) => console.log(err));
        this.modalViewed = 0;
        console.log(this.position)
        // this.$dispatch.route('Profile')
        this.$router.push({name: 'Counselors'})
        // window.location.reload(); // 새로 고침
        // alert("상담이 신청되었습니다.")
      } else {
        this.$router.push({name: 'LoginForClient'})
      }
    }
  },
  computed: {
    ...mapState([
      'counselorDetail',
    ]),
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
#counselorschedule .schedule-btn1 {
  width: 100%;
  font-size: 20px;
  padding: 5px;
  background: #64C7FF;
  border-radius: 30px;
  border: none;
}

#counselorschedule .schedule-btn2 {
  width: 100%;
  font-size: 20px;
  padding: 5px;
  background: #D9D9D9;
  border-radius: 30px;
  border: none;
}

#counselorschedule .schedule-btn3 {
  width: 100%;
  font-size: 20px;
  padding: 5px;
  background: #FF9999;
  border-radius: 30px;
  border: none;
}

#counselorschedule table {
  width: 100%;
  display: table;
  table-layout: fixed;
  font-size: 20px;
  background-color: rgb(255, 255, 255);
  opacity: 0.8;
  border-collapse: collapse;
  text-align: center;
}

#counselorschedule th, td {
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