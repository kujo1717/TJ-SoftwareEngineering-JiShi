<template>
    <div class="loginBox">
        <el-form :model="userFormInfo" :rules="rules" ref="userFormInfo" label-width="100px" class="userForm"
            label-position="top">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="userFormInfo.username" autocomplete="off" class="input-box"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="userFormInfo.password" autocomplete="off" class="input-box">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-checkbox v-model="userFormInfo.remeber">记住我</el-checkbox>
            </el-form-item>
            <el-form-item>
                <el-button type="success" @click="onLogin">登录</el-button>
                <el-button type="primary" @click="onRegister" style="margin-left: 50px;">注册</el-button>
            </el-form-item>
            
        </el-form>
    </div>
</template>
<script>
export default {
    data() {
        var checkName = (rule, value, callback) => {
            if (!value) {
                callback(new Error("用户名不能为空"))
            }
            callback();
        }
        var checkPwd = (rule, value, callback) => {
            if (!value) {
                callback(new Error("密码不能为空"))
            }
            callback();
        }
        return {
            userFormInfo: {
                username: '',
                password: '',
                remeber: false
            },
            rules: {
                username: [
                    { validator: checkName, tigger: 'blur' }
                ],
                password:[
                    { validator:checkPwd,tigger:'blur'}
                ]
            }
        };
    },
    methods: {
        onLogin() {
            this.$refs['userFormInfo'].validate((valid)=>{
                if(valid){
                    alert('submit');
                    console.log(this.userFormInfo)
                } else{
                    console.log('error submit');
                    return false;
                }
            })
        },
        onRegister() {
            console.log('register')
        }
    }
}
</script>
<style lang="less" scoped>
.loginBox {
    height: 100%;
    background-color: aqua;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

}

.userForm {
    background-color: white;
    padding-left: 100px;
    padding-right: 100px;
    padding-top: 50px;
}
</style>