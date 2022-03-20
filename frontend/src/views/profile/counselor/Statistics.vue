<template>
  <div id="statistics" class="col-10 p-routing overflow-auto" style="91vh;">
    <!-- title -->
    <div class="mb-5 d-flex justify-content-between align-items-center">
      <p class="mb-0 f-title">통계 정보</p>
    </div>

    <!-- body -->
    <div class="mb-3 d-flex justify-content-between">
      <!-- 내담자의 성비 -->
      <section>
        <div class="mb-5 part-stat-square d-flex justify-content-center align-items center">
          <gender/>
        </div>
        <p class="text-center f-normal">내담자의 성별 비율</p>
      </section>

      <!-- 내담자의 연령별 빈도 -->
      <section>
        <div class="mb-5 part-stat-square d-flex justify-content-center align-items center">
          <age/>
        </div>
        <p class="text-center f-normal">내담자의 연령별 빈도</p>
      </section>

      <!-- 월별 상담수 -->
      <section>
        <div class="mb-5 part-stat-square d-flex justify-content-center align-items center">
          <month/>
        </div>
        <p class="text-center f-normal">월별 상담수</p>
      </section>
    </div>
  </div>
</template>

<script>
import Gender from '@/components/listener/Gender.vue'
import Age from '@/components/listener/Age.vue'
import Month from '@/components/listener/Month.vue'

export default {
  name:'Statistics',

  components: {
    Gender,
    Age,
    Month,
  },
}
</script>

<style scoped>
.part-stat-square {
  width: 20vw;
  height: 20vw;

  background: #fff4e8;
  border-radius: 20px;
  filter: drop-shadow(4px 4px 15px rgba(0, 0, 0, 0.3));
}
</style>
