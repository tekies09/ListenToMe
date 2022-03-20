<script>
import axios from 'axios'
import SERVER from '@/api/index.js'
import { Pie } from "vue-chartjs"

export default {
  name: 'Gender',

  extends: Pie,
  
  data() {
    return {
      userColumn: [],
      userCount: [],
      datacollection: {
        labels: [], 
        datasets: [{
          label: 'Data',
          backgroundColor: ['#87CEFA', '#f87979'],
          pointBackgroundColor: 'white',
          borderWidth: 1,
          pointBorderColor: '#249EBF',
          data: []  //Data to be represented on y-axis
        }]
      },
      options: {
        scales: {
          xAxes: [{
            gridLines: {
              display: false
            }
          }]
        },
        responsive: true,
        maintainAspectRatio: true,
      }
    }
  },
    
  async mounted(){
    this.getGender()  
  },

  methods:{
    getGender: function(){
      axios({
        method: 'get',
        url: SERVER.URL + SERVER.ROUTES.getGender,
        headers:{
          'access-token': `${this.$store.state.authToken}`
        },
      })
        .then(res=> {
          console.log(res);
          console.log("gender")
          console.log(res.data)

          this.userColumn = ['f', 'm'];
          this.datacollection.labels = this.userColumn

          this.userCount = res.data
          this.datacollection.datasets[0].data = this.userCount
          this.renderChart(this.datacollection, this.options)
        })
    }
  }
}
</script>
