/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.utils;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.schedular.commons.MessageType;


/**
 * The Class TemplateService.
 */
@Service
public class TemplateService {

  /** The velocity engine. */
  @Autowired
  private VelocityEngine velocityEngine;

  @Value("${application.url}")
  private String applicationUrl;

  public TemplateService() {
    super();
  }

  /**
   * Sets the activate user.
   *
   * @param urlToSend the url to send
   * @param userName the user name
   * @param name the name
   * @return the string
   */
  public String setActivateUser(String urlToSend, String userName, String name) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("User_Credentials_Activation.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("urlToSend", urlToSend);
    context.put("username", userName);
    context.put("name", name);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  public String assetCollectionMailTemplate(String orderNumber, String loginPageURL) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Asset_Collection_Order.vm");
    } catch (Exception e) {
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("loginPageURL", loginPageURL);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  /**
   * Reset password mail template.
   *
   * @param urlToSend the url to send
   * @param userName the user name
   * @param name the name
   * @return the string
   */
  public String resetPasswordMailTemplate(String urlToSend, String userName, String name) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Reset_Password.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("urlToSend", urlToSend);
    context.put("username", userName);
    context.put("name", name);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  /**
   * Sets the activate admin.
   *
   * @param urlToSend the url to send
   * @param userName the user name
   * @param name the name
   * @return the string
   */
  public String setActivateAdmin(String urlToSend, String userName, String name) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Admin_Credentials_Activation.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("urlToSend", urlToSend);
    context.put("username", userName);
    context.put("name", name);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  /**
   * Order creation template.
   *
   * @param orderNumber the order number
   * @param updationDate the updation date
   * @param shipFromLoc the ship from loc
   * @param shipToLoc the ship to loc
   * @return the string
   */
  public String orderCreationTemplate(String orderNumber, String updationDate, String shipFromLoc,
      String shipToLoc) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Order_Creation.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("updationDate", updationDate);
    context.put("shipFromLoc", shipFromLoc);
    context.put("shipToLoc", shipToLoc);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  /**
   * Order creation template.
   *
   * @param orderNumber the order number
   * @param updationDate the updation date
   * @param shipFromLoc the ship from loc
   * @param shipToLoc the ship to loc
   * @return the string
   */
  public String orderModificationTemplate(String orderNumber, String updationDate,
      String shipFromLoc, String shipToLoc) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Order_Modification.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("updationDate", updationDate);
    context.put("shipFromLoc", shipFromLoc);
    context.put("shipToLoc", shipToLoc);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  /**
   * Order Modification template.
   *
   */
  public String rackRecoveryOrderModificationTemplate(String orderNumber, Integer quantity,
      String pickUpDueDate, String parantOrder) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Rack_Recovery_Order_Modification.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("quantity", quantity);
    context.put("pickUpDueDate", pickUpDueDate);
    context.put("parantOrder", parantOrder);


    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  /**
   * Order Creation template.
   *
   */
  public String rackRecoveryOrderCreationTemplate(String orderNumber, Integer quantity,
      String pickUpDueDate, String parantOrder) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Rack_Recovery_Order_Creation.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("quantity", quantity);
    context.put("pickUpDueDate", pickUpDueDate);
    context.put("parantOrder", parantOrder);


    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }



  /**
   * Order cancelation template.
   *
   * @param orderNumber the order number
   * @param updationDate the updation date
   * @param shipFromLoc the ship from loc
   * @param shipToLoc the ship to loc
   * @return the string
   */
  public String orderCancelationTemplate(String orderNumber, String updationDate,
      String shipFromLoc, String shipToLoc) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Order_Cancelation.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("updationDate", updationDate);
    context.put("shipFromLoc", shipFromLoc);
    context.put("shipToLoc", shipToLoc);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  /**
   * Outbound order assignment template.
   *
   * @param orderNumber the order number
   * @return the string
   */
  public String outboundOrderAssignmentTemplate(String orderNumber) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Outbound_Order.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  public String transitOrderTemplate(String orderNumber, String orderSubjectStatus,
      String orderStatus, String orderStatusMessage, String providerName, String updationDate) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Transit_Order.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("orderSubjectStatus", orderSubjectStatus);
    context.put("orderStatus", orderStatus);
    context.put("orderStatusMessage", orderStatusMessage);
    context.put("providerName", providerName);
    context.put("updationDate", updationDate);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  public String carrierTemplate(String orderNumber, String orderSubjectStatus, String orderStatus,
      String orderStatusMessage, String carrierName, String updationDate) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Carrier.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("orderSubjectStatus", orderSubjectStatus);
    context.put("orderStatus", orderStatus);
    context.put("orderStatusMessage", orderStatusMessage);
    context.put("carrierName", carrierName);
    context.put("updationDate", updationDate);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  public String orderDeliverTemplate(String orderNumber, String providerName, String updationDate) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Order_Deliver.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("providerName", providerName);
    context.put("updationDate", updationDate);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  public String orderRecevingTemplate(String orderNumber, String type,
      List<String[]> orderInfoList) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Order_Receving.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("type", type);
    context.put("orderInfoList", orderInfoList);
    context.put("applicationUrl", applicationUrl.concat("/ezr_phase2"));

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }


  public String notEnoughInventoryTemplate(List<String[]> skuInfoList) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Insufficient_Inventory.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("skuInfoList", skuInfoList);


    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }



  public String orderDriverDispatchedTemplate(String orderNumber, String providerName,
      String updationDate) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Order_Driver_Dispatch.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("providerName", providerName);
    context.put("updationDate", updationDate);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  /**
   * Order picked up template.
   *
   * @param orderNumber the order number
   * @param pickUpdate the pick update
   * @param shipFromLocRef the ship from loc ref
   * @param shipFromLocName the ship from loc name
   * @param shipFromLocAdd1 the ship from loc add 1
   * @param shipFromLocAdd2 the ship from loc add 2
   * @param shipFromLocCity the ship from loc city
   * @param shipFromLocState the ship from loc state
   * @param shipFromLocZip the ship from loc zip
   * @param shipFromLocCountryCode the ship from loc country code
   * @param pickUpSignatureBase64String the pick up signature base 64 string
   * @param orderComponents the order components
   * @return the string
   */
  public String orderPickedUpTemplate(String orderNumber, Date pickUpdate, String shipFromLocRef,
      String shipFromLocName, String shipFromLocAdd1, String shipFromLocAdd2,
      String shipFromLocCity, String shipFromLocState, String shipFromLocZip,
      String shipFromLocCountryCode, String pickUpSignatureBase64String,
      List<Map<String, Integer>> orderComponents) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("OrderPickedUp.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("pickUpDate", new SimpleDateFormat("MM/dd/yy").format(pickUpdate));
    context.put("pickUpTime", new SimpleDateFormat("h:mm aa").format(pickUpdate));
    context.put("shipFromLocRef", shipFromLocRef);
    context.put("shipFromLocName", shipFromLocName);
    context.put("shipFromLocAdd1", shipFromLocAdd1);
    context.put("shipFromLocAdd2", shipFromLocAdd2);
    context.put("shipFromLocCity", shipFromLocCity);
    context.put("shipFromLocState", shipFromLocState);
    context.put("shipFromLocZip", shipFromLocZip);
    context.put("shipFromLocCountryCode", shipFromLocCountryCode);
    context.put("pickUpSignatureBase64String", pickUpSignatureBase64String);
    context.put("orderComponents", orderComponents);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }


  /**
   * Rental order create.
   *
   */
  public String rentalOrderCreateTemplate(String parentOrderNumber, List<String[]> orderInfoList) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Rental_Order_Creation.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("parentOrderNumber", parentOrderNumber);
    context.put("orderInfoList", orderInfoList);
    StringWriter writer = new StringWriter();
    template.merge(context, writer);
    // System.out.println("ye rhi template---> "+ writer.toString());
    return writer.toString();
  }

  public String rentalOrderUpdateTemplate(String parentOrderNumber, List<String[]> orderInfoList) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Rental_Order_Updation.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("parentOrderNumber", parentOrderNumber);
    context.put("orderInfoList", orderInfoList);
    StringWriter writer = new StringWriter();
    template.merge(context, writer);
    // System.out.println("ye rhi update ki template---> "+ writer.toString());
    return writer.toString();
  }

  public String shortShipmentTemplate(String orderNumber, List<String[]> orderInfoList,
      String shortShiporderNumber) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Short_shipment_order.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("shortShiporderNumber", shortShiporderNumber);
    context.put("orderInfoList", orderInfoList);
    StringWriter writer = new StringWriter();
    template.merge(context, writer);
    return writer.toString();
  }

  public String rentalOrderCancelTemplate(String parentOrderNumber, List<String[]> orderInfoList,
      Date cancleDate) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Rental_Order_Cancelled.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("parentOrderNumber", parentOrderNumber);
    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cancleDate);
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat outputFormat = new SimpleDateFormat("'Date : 'dd-MM-yyyy / KK:mm a");
    try {
      context.put("cancelTime", outputFormat.format(inputFormat.parse(date)));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    context.put("orderInfoList", orderInfoList);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);
    return writer.toString();
  }

  public String workOrderCancelationTemplate(String orderNumber, String[] orderInfo,
      Date cancleDate) {
    velocityEngine.init();

    Template template = null;
    try {
      template = velocityEngine.getTemplate("Work_Order_Cancelation.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cancleDate);
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat outputFormat = new SimpleDateFormat("'Date : 'dd-MM-yyyy / KK:mm a");
    try {
      context.put("cancelTime", outputFormat.format(inputFormat.parse(date)));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    context.put("orderInfo", orderInfo);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);
    return writer.toString();
  }

  public String assignProviderTemplate(String orderNumber, String[] orderInfo) {
    velocityEngine.init();
    Template template = null;
    try {
      template = velocityEngine.getTemplate("Assign_Provider.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("orderInfo", orderInfo);
    context.put("applicationUrl", applicationUrl.concat("/ezr_phase2"));

    StringWriter writer = new StringWriter();
    template.merge(context, writer);
    return writer.toString();
  }

  public String racksRecoveryAssignProviderTemplate(String orderNumber) {
    velocityEngine.init();
    Template template = null;
    try {
      template = velocityEngine.getTemplate("Rack_Recovery_Assign_Provider.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("orderNumber", orderNumber);
    context.put("loginPageURL", applicationUrl.concat("/ezr_phase2"));

    StringWriter writer = new StringWriter();
    template.merge(context, writer);
    return writer.toString();
  }

  public String shortShipmentTemplate() {
    velocityEngine.init();
    Template template = null;
    try {
      template = velocityEngine.getTemplate("Short_Shipment.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    StringWriter writer = new StringWriter();
    template.merge(context, writer);
    return writer.toString();
  }

  public String growerNotificationTemplate(List<String[]> mailDataList, String type) {
    velocityEngine.init();
    Template template = null;
    try {
      template = velocityEngine.getTemplate("Grower_Notification.vm");
    } catch (Exception e) {
      e.printStackTrace();
      LogManager.error(getClass(), e.getMessage(), MessageType.Error);
    }
    if (template == null)
      return null;

    VelocityContext context = new VelocityContext();
    context.put("detailsList", mailDataList);
    context.put("type", type);

    StringWriter writer = new StringWriter();
    template.merge(context, writer);
    return writer.toString();
  }
}
