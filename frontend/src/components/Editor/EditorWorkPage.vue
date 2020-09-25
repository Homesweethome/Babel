<template>
    <div>
<!--        фон с планом здания-->
        <svg class="scheme">
            <img src="../../assets/scheme/1.png" alt="">
        </svg>
<!--        отображает элементы карты-->
        <svg>
            <home-element-wrapper />
            <home-element />
            <node-wrapper />
        </svg>
    </div>
</template>

<script>
    import HomeElementWrapper from "@/components/HomeElement/HomeElementWrapper";
    import HomeElement from "@/components/HomeElement/HomeElement";
    import NodeWrapper from "@/components/Node/NodeWrapper";
    export default {
        name: "EditorWorkPage",
        components: {NodeWrapper, HomeElement, HomeElementWrapper}
    }
</script>

<style scoped>
    scheme{
        opacity: 0.5;
        filter: invert(1);
    }
    svg {
        position: absolute;
        top: 0;
        left: 30px;
        width: 100%;
        height: 100%;
        z-index: 0;
    }
</style>
