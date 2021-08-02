package com.fusion.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashboardPage extends BasePage{

    public DashboardPage() {
        super();
        initElements();
    }

    @FindBy(how = How.XPATH, using = "//table[contains(@pl_prop_class,'Assign-WorkBasket')]/tbody/tr[1]//a[1]")
    public WebElement workBasketIdFilter;

    public WebElement workBasketItem(String value) {
        return wd.findElement(By.xpath("//table[contains(@pl_prop_class,'Assign-WorkBasket')]/tbody/tr//a[text()='"+value+"']"));
    }

    public WebElement workQueueItem(String value) {
        return wd.findElement(By.xpath("//span[@data-name='"+value+"']"));
    }
}
