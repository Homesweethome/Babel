<template>
    <v-container class="white--text">
        <v-row>
            {{selectedHomeElement}}
            {{selectedNode}}
            <v-col cols="12">
                <v-subheader class="subtitle-1">
                    Информация о объекте
                </v-subheader>
            </v-col>
            <v-col cols="12">
                <v-text-field
                        v-model="data.name"
                        label="Название"/>
            </v-col>
            <v-col
                    cols="12">
                <v-textarea
                        rows="5"
                        v-model="data.description"
                        label="Описание"/>
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-btn @click="deleteElement" class="error" block>
                    Удалить
                </v-btn>
            </v-col>
            <v-col>
                <v-btn :disabled="this.selectNode" @click="save" class="primary">
                    Сохранить
                </v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import {mapState, mapActions} from 'vuex'
    export default {
        name: "EditorPropertiesElement",
        data(){
            return{
                name:'',
                    data: {}
            }
        },
        computed:{
            ...mapState('editor', {
                selectedNode: 'selectedNode',
                selectedHomeElement: 'selectedHomeElement',
                selectHomeElement: 'selectHomeElement',
                selectNode: 'selectNode',
                nodeElements: 'nodeElements',
                homeElements: 'homeElements',
            })
        },
        methods:{
            ...mapActions('editor', {
                deleteHomeElement: 'delete_home_element',
                deleteNode: 'delete_node',
                getAllNodeElements: 'get_all_node_elements',
                addHomeElement: 'add_home_element',
                addNode: 'add_node',
                unselect: 'unselect'
            }),
            deleteElement(){
                if (this.selectHomeElement){
                    this.deleteHomeElement(this.selectedHomeElement)
                } else if (this.selectNode){
                    this.deleteNode(this.selectedNode)
                }
            },
            save(){
                if (this.selectHomeElement){
                    this.selectedHomeElement
                    this.addHomeElement(this.data)
                }
                this.unselect()
            }
        },
        mounted() {

        }
    }
</script>

<style scoped>

</style>
