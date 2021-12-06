package ${package.Controller};

<#if superControllerClass??>
import ${superControllerClassPackage};
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
</#if>
<#if swagger>
import io.swagger.annotations.ApiModel;
</#if>
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>

/**
 * <p>
 *${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
lass ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
 <#if swagger>
@ApiModel(value = "${entity}", description = "${table.comment!}")
 </#if>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass}<${entity}, ${table.serviceName}> {
<#else>
public class ${table.controllerName} {
</#if>

}
</#if>
