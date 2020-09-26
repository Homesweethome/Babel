<template>
    <div style="margin-top: 500px">
        <div @click="onClick">
            <svg>
                <image
                        style="opacity: 0.5; filter: invert(1)"
                        :href="'data:image/png;base64,' + selectFloor.image"
                        x="50"
                        y="60"
                        width="1000"
                />
            </svg>
            <svg
                    @mousemove="onMouseMove"
            ><g>

                <home-element-wrapper />
<!--                <polyline points="187,97 406,145 406,135 614,152 612,94" style="fill:none;stroke:black;stroke-width:3"></polyline>-->

                <node-wrapper />

            </g>
            </svg>
            <v-btn
                    fab
                    color="cyan accent-2"
                    bottom
                    right
                    fixed
                    @click="searchPath"
            >
                <v-icon>mdi-search</v-icon>
            </v-btn>
        </div>
    </div>
</template>

<script>
    import {mapActions, mapState} from 'vuex'
    import HomeElementWrapper from "@/components/HomeElement/HomeElementWrapper";
    import NodeWrapper from "@/components/Node/NodeWrapper";
    export default {
        name: "MapForUser",
        components: {NodeWrapper, HomeElementWrapper},
        data(){
            return{
                items: [
                    'К/Х-АТС',
                    'К/Х1',
                    'ИСК',
                    'ИСК-НС',
                    'ОГЛ',
                    'БИБЛИОГРАФ'
                ]
            }
        },
        computed: {
            ...mapState('editor', {
                modeEditor: 'modeEditor',
                drawTypeElement: 'drawTypeElement',
                selectFloorId: 'selectFloorId',
                floors: 'floors'
            }),
            drawHomeElement(){
                return this.modeEditor==='draw'
            },
            selectFloor(){
                return this.floors.find(n=>n.id==this.selectFloorId)
            }
        },
        methods: {
            ...mapActions('editor', {
                addHomeElement: 'add_home_element',
                addNode: 'add_node',
                unselect: 'unselect',
                getAllHomeElements: 'get_all_home_elements',
            }),
        },
        mounted() {
            this.getAllHomeElements()
        }
    }
</script>

<style scoped>
        /*svg {*/
        /*    position: absolute;*/
        /*    top: 100px;*/
        /*    left: 30px;*/
        /*    width: 100%;*/
        /*    height: 100%;*/
        /*    z-index: 0;*/
        /*}*/
</style>
