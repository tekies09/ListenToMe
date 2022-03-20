<template>
  <div id="record-list" class="p-4 part-record">
    <div class="mb-5 d-flex justify-content-between align-items-center">
      <p class="mb-0 f-subtitle">메모 보기</p>
    </div>

    <div class="row row-cols-5 g-5">
      <div v-for="memo in memos" :key=memo.id>
        <div class="col">
          <div class="card part-counselor">
            <div class="card-body px-4">
              <p class="mb-3 f-subtitle">{{ memo.title }}</p>
              <p class="mb-3 f-normal">{{ memo.date }}</p>
              <p class="mb-0 f-normal">{{ memo.content }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'List',

  props: {
    memos: {
      type: Array
    }
  },
}
</script>
