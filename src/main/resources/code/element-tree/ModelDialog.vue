/*${cnName}新增与修改,  作者:${author},  日期:${time}*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false" @close="clear">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
  <#list fields as field>
  <#if (field.fillType != 0)>
        <#if field.fillType == 2><el-col><#else><el-col :span="12"></#if>
          <el-form-item prop="${field.lower}" label="${field.comment}">
        <#if field.fillType == 3>
            <v-select placeholder="请选${field.comment}" v-model="form.${field.lower}" dictKey="${field.dictKey}" :addBlank="true"></v-select>
        <#elseif field.fillType == 4>
            <el-date-picker size="mini" placeholder="请选${field.comment}" v-model="form.${field.lower}" type="datetime"></el-date-picker>
        <#else>           
            <el-input size="mini" placeholder="请输入${field.comment}" v-model="form.${field.lower}"<#if field.type == "String"> maxlength="${field.length}" show-word-limit</#if><#if field.fillType == 2> type="textarea" rows="3" cols="85"</#if> />
        </#if>           
          </el-form-item>
        </el-col>
  </#if>
  </#list>
      </el-row>
    </el-form>
    <v-footer :close="() => show = false" :save="save"></v-footer>
  </el-dialog>
</template>
<script>
import { dialogMix } from "@/common/dialog";
export default {
  mixins: [dialogMix],	
  props: ["queryTree"],
  components: {},
  updated() { this.titles("${cnName}"); },  
  data() {
    return {
      rules: {
<#list fields as field>
<#if (field.fillType != 0)>
        ${field.lower}: [{ required: true, message: '请输入${field.comment}', trigger: 'blur' }],
</#if>         
</#list>
      },
    };
  },
  methods: {
    /*${cnName}-保存*/
    save() {
      this.${dollar}refs['form'].validate(valid => {
        if (!valid) return;
        this.rq.post("${serverName}/${classname}/" + this.dialogMode, this.form).then(res => {
          if (res.code == 200) {
            this.show = false;
            this.${dollar}message.success(this.title + "成功!");
            this.queryTree();
          } else this.$message.error(res.msg);
        });
      });
    },
    /*${cnName}-初始数据*/
    initForm() {
      return {
<#list fields as field>
  <#if (field.fillType != 0)>
        ${field.lower}: null,/*${field.comment}*/
  </#if>
</#list>
      };
    },
  },
};
</script>
<style scoped lang="scss"></style>
