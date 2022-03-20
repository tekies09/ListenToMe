<template>
  <div id="record-item" class="mb-3 p-3 part-record">
    <!-- header: 제목 -->
    <div class="d-flex justify-content-between align-items-center">
      <p class="mb-0 text-center f-subtitle">{{ memo.title }}</p>
      <p class="mb-0 text-center f-normal">{{ dateTimeTrimmed }}</p>
    </div>

    <hr class="my-3">

    <!-- body: 내용 -->
    <p class="mb-0 py-2 f-normal">{{ memo.content }}</p>
  </div>
</template>

<script>
export default {
  name: 'RecordItem',

  props: {
    memo: {
      type: Object,
    },
  },

  data: function () {
    return {
      dateTimeTrimmed: ''
    }
  },

  methods: {
    trimDateTime: function () {
      this.dateTimeTrimmed = this.memo.dateTime.slice(0, 10) + ' ' + this.memo.dateTime.slice(11)
    }
  },

  created () {
    this.trimDateTime()
  }
}
</script>
