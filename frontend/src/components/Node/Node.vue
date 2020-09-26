<template>
    <!--    <g>-->
    <!--        <foreightObject-->
    <!--                :x="data.positionStart.x"-->
    <!--                :y="data.positionStart.y"-->
    <!--                :width="64"-->
    <!--                :height="64"-->
    <!--        >-->
    <!--            <v-icon class="node-badge">-->
    <!--                mdi-trash-->
    <!--            </v-icon>-->
    <!--        </foreightObject>-->
    <!--    </g>-->
    <g @click="select">
        <circle :cx="data.position.x"
                :cy="data.position.y"
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
        computed:{
            ...mapState('editor',{
                modeEditor: 'modeEditor',
                selectedNode: 'selectedNode'
            }),
        },
        methods:{
            ...mapActions('editor', {
                selectNode: 'select_node'
            }),
            select(e){
                e.preventDefault();
                if (this.modeEditor=='draw'){
                    return;
                }
                if (this.data.newElement) {
                    return;
                }
                if (!this.data.newElement) {

                    this.selectNode(this.data)
                    e.stopPropagation();
                }

            }
        }
    }
</script>

<style scoped>
    .node-badge{
        width: 64px;
        height: 64px;
        margin: -32px -32px;
    }
</style>
