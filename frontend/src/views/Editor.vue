<template>
    <div>
        <v-container fluid>
            <v-app-bar app height="64">
                <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
                <h3>{{modeEditorText}}</h3>
                <v-spacer/>
                <editor-floors show-btn-edit="true" />
            </v-app-bar>
            <v-navigation-drawer
                    v-model="drawer"
                    width="330"
                    permanent
                    app>
                <v-row class="fill-height" no-gutters>
                    <v-navigation-drawer
                            dark
                            mini-variant
                            mini-variant-width="56"
                    >
                        <v-list-item class="px-2">
                            <v-list-item-avatar>
                                <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
                            </v-list-item-avatar>
                        </v-list-item>

                        <v-divider></v-divider>

                        <v-list
                                dense
                                nav
                        >

                            <v-list-item
                                    v-for="item in items"
                                    :key="item.title"
                            >
                                <v-list-item-action>
                                    <v-icon>{{ item.icon }}</v-icon>
                                </v-list-item-action>

                                <v-list-item-content>
                                    <v-list-item-title>{{ item.title }}</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </v-list>
                    </v-navigation-drawer>

                    <editor-work-element />
                </v-row>
            </v-navigation-drawer>
            <v-main class="mainWindow">
                <editor-work-page />
            </v-main>
            <v-navigation-drawer app right v-model="activeRightBar"
                                 width="400">
                <editor-properties-element />
            </v-navigation-drawer>

            <v-footer>

            </v-footer>
        </v-container>
    </div>
</template>

<script>
    import EditorWorkPage from "@/components/Editor/EditorWorkPage";
    import EditorWorkElement from "../components/Editor/EditorWorkElement";
    import {mapState} from 'vuex'
    import EditorPropertiesElement from "../components/Editor/EditorPropertiesElement";
    import EditorFloors from "../components/Editor/EditorFloors";
    export default {
        name: "Editor",
        components: {EditorFloors, EditorPropertiesElement, EditorWorkElement, EditorWorkPage},
        data: () => ({
            drawer: null,
            drawerRight: false,
            items: [
                { title: 'Home', icon: 'dashboard' },
                { title: 'About', icon: 'question_answer' },
            ],
        }),
        computed:{
            ...mapState('editor', {
                selectNode: 'selectNode',
                selectedNode: 'selectedNode',
                selectHomeElement: 'selectHomeElement',
                selectedHomeElement: 'selectedHomeElement',
                modeEditor: 'modeEditor'
            }),
            activeRightBar:{
                get(){
                    return  this.selectHomeElement || this.selectNode
                },
                set(){
                    return
                }
            },
            modeEditorText(){
                return this.modeEditor=='draw' ? 'Режим рисования' : 'Режим выбора объектов'
            }
        },
        created () {
            this.$vuetify.theme.dark = true
        }
    }
</script>

<style scoped>
    .mainWindow {
        position: absolute;
        top: 0;
        left: 30px;
        width: 100%;
        height: 100%;
        z-index: 0;
    }
    .v-main__wrap{
        padding: 0;
    }
</style>
