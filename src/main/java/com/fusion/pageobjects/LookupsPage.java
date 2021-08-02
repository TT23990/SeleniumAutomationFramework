package com.fusion.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public class LookupsPage extends BasePage {
    public LookupsPage() {
        super();
        initElements();
    }
    public WebElement rejectButton(String value) {
        return getWebDriver().findElement(By.xpath("//button[@class='Cancel_Large pzhc pzbutton']"));
    }

    public List<WebElement> errorList() {
        return wd.findElements(By.xpath("//span[@class='iconError dynamic-icon-error']"));
    }

}
