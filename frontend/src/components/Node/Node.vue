<template>
    <g @click="select">
        <circle :cx="data.positionStart.x"
                :cy="data.positionStart.y"
                r="10"
                stroke="black"
                stroke-width="3"
                fill="red" />
    </g>
</template>

<script>
    import {mapActions, mapState} from 'vuex'
    export default {
        name: "Node",
        props: {
            data: Object
        },
        data() {
            return {
                title: '',
                description: '',
                id: '',
                position: {
                    x: '',
                    y: '',
                },
                roomId: '',
                floor: '',
                icon: '',
            }
        },
        computed: {
            ...mapState('editor', {
                modeEditor: 'modeEditor',
                selectedNode: 'selectedNode'
            }),
        },
    }
</script>

<style scoped>

</style>
