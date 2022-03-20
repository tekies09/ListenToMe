<script>
import axios from 'axios'
import SERVER from '@/api/index.js'
import { Bar } from 'vue-chartjs'

export default {
  name: 'Age',

  extends: Bar,

  data: () => ({
    chartdata: {
      labels: [],
      datasets: [{
        label: '연령별',
        backgroundColor: '#f87979',
        data: [40, 60, 30,10]
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false
    }
  }),

  async mounted () {
    this.getAge()
    this.renderChart(this.chartdata, this.options)
  },

  methods:{
    getAge: function () {
      axios({
        method: 'get',
        url: SERVER.URL + SERVER.ROUTES.getAge,
        headers:{
          'access-token': `${this.$store.state.authToken}`
        },
      })
      .then(res=> {
        console.log("age")
        console.log(res.data)
        
        this.chartdata.labels = res.data.age_list
        this.chartdata.datasets[0].data = res.data.age_value
        this.chartdata.datasets[0].data[2] = 1
        console.log(this.chartdata.datasets[0].data)
        this.renderChart(this.chartdata, this.options)
      })
    }
  }
}
</script>
