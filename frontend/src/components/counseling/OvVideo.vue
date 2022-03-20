<template>
	<video
		id="ov-video"
		:class="{
			'part-video-hor': !$store.state.isData,
			'part-video-ver': $store.state.isData,
			'position-absolute': $store.state.isSticker
		}"
		autoplay
	/>
</template>

<script>
export default {
	name: 'OvVideo',

	props: {
		streamManager: Object,
	},

	mounted () {
		this.streamManager.addVideoElement(this.$el);
	},
}
</script>
