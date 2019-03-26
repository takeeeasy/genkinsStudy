package com.com.com.nexacroController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NexacroController {

	@RequestMapping(value = "/{name}.do")
	public String home(@PathVariable String name) {
		System.out.println(name+"!!");
		return "resources/serverTest01/QuickView.html?formname=Base::"+name;
	}
	
	@RequestMapping(value = "/nexacro14lib/framework/{name}")
	public String nexa14lib_framework(@PathVariable String name) {
		System.out.println(name+"!!");
		return "resources/serverTest01/nexacro14lib/framework/"+name;
	}
	
	@RequestMapping(value = "/nexacro14lib/component/CompBase/{name}")
	public String nexa14lib_CompBase(@PathVariable String name) {
		System.out.println(name+"!!");
		return "resources/serverTest01/nexacro14lib/component/CompBase/"+name;
	}

	@RequestMapping(value = "/nexacro14lib/component/ComComp/{name}")
	public String nexa14lib_ComComp(@PathVariable String name) {
		System.out.println(name+"!!");
		return "resources/serverTest01/nexacro14lib/component/ComComp/"+name;
	}
	
	@RequestMapping(value = "/nexacro14lib/component/Grid/{name}")
	public String nexa14lib_Grid(@PathVariable String name) {
		System.out.println(name+"!!");
		return "resources/serverTest01/nexacro14lib/component/Grid/"+name;
	}
	
	@RequestMapping(value = "/nexacro14lib/component/DeviceAPI/{name}")
	public String nexa14lib_DeviceAPI(@PathVariable String name) {
		System.out.println(name+"!!");
		return "resources/serverTest01/nexacro14lib/component/DeviceAPI/"+name;
	}
	
	@RequestMapping(value = "/{name}")
	public String nexa_xadl(@PathVariable String name) {
		System.out.println(name+"!!");
		return "resources/serverTest01/"+name;
	}
	
	@RequestMapping(value = "/_theme_/default/images/{name}")
	public String nexa_theme(@PathVariable String name) {
		System.out.println(name+"!!");
		return "resources/serverTest01/_theme_/default/images/"+name;
	}
	
	@RequestMapping(value = "/_theme_/default/{name}")
	public String nexa_theme_img(@PathVariable String name) {
		System.out.println(name+"!!");
		return "resources/serverTest01/_theme_/default/"+name;
	}
	
	@RequestMapping(value = "/nexacro14lib/resources/images/{name}")
	public String nexa_img(@PathVariable String name) {
		System.out.println(name+"!!");
		return "resources/serverTest01/nexacro14lib/resources/images/"+name;
	}
	
}
