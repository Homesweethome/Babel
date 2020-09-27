<template>
    <g>
        <rect
                :x="data.positionStart.x"
                :y="data.positionStart.y"
                :width="data.size.width"
                :height="data.size.height"
                :stroke="selectedRoom"
                stroke-width="5"
                stroke-dasharray="20"
                fill="#eee"
                fill-opacity=".4"
        />
        <foreignObject  :x="data.positionStart.x"
                        :y="data.positionStart.y"
                        :width="data.size.width"
                        :height="data.size.height">
            <div style="color: red;" class="text-center">
                {{data.name}}
            </div>
        </foreignObject>
    </g>
</template>

<script>
    import {mapActions, mapState} from 'vuex'
    export default {
        name: "Room",
        props: {
            data: Object
        },
        data(){
            return{

            }
        },
        computed:{
            ...mapState('editor',{
                modeEditor: 'modeEditor',
                selectedHomeElement: 'selectedHomeElement'
            }),
            colorStroke(){
                return this.data.newElement ? "red" : "#1565c0"
            },
            selectedRoom(){
                return this.selected ? "green" : this.colorStroke
            },
            selected(){
                return this.data.id == this.selectedHomeElement.id
            }
        },
        methods:{
            ...mapActions('editor', {
                selectHomeElement: 'select_home_element'
            }),
            selectRoom(e){
                e.preventDefault();
                if (this.modeEditor=='draw'){
                    return;
                }
                if (this.data.newElement) {
                    return;
                }
                if (!this.data.newElement) {

                    this.selectHomeElement(this.data)
                    e.stopPropagation();
                }

            }
        }
    }
</script>

<style scoped>

</style>
