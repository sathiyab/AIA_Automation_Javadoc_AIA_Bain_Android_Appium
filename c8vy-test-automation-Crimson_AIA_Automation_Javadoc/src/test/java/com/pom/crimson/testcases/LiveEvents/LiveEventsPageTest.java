package com.pom.crimson.testcases.LiveEvents;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.pages.AllLiveEventsPage;
import com.pom.crimson.pages.ContactUsPage;
import com.pom.crimson.pages.EventDetailsPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.InterestsPage;
import com.pom.crimson.pages.LiveEventsPage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.util.ExtentReportManager;
import com.pom.crimson.api.*;
import com.pom.crimson.pages.MyLiveEventsPage;

import io.restassured.response.Response;
import com.pom.crimson.listeners.LogAssert;
import io.restassured.path.json.*;

/**
 * This class contains test methods to test Live Events module in mobile App
 *
 * 
 * @author Sivaprakash.Selvaraj
 */
public class LiveEventsPageTest extends BaseFixture {

	HomePage homePage;
	LoginPage loginPage;
	LiveEventsPage liveEventsPage;
	GroupAdviceAPI GP;
	UserProfileControllerAPI UC;
	LogAssert logAssert;

	/**
	 * Method to initialize the respective Rest Assured API objects.
	 * 
	 * @throws Exception throws all exception
	 */	
	@BeforeTest()
	public void beforeTestLocal() throws Exception {
		GP = new GroupAdviceAPI();
		UC = new UserProfileControllerAPI(); 
		Response respAssignedEventList = GP.GetEventsByUser("UPCOMING");
		List<String> AssingedEventList = respAssignedEventList.jsonPath().getList("value.listid");

		if (respAssignedEventList.statusCode() == 200) {
			for (int i = 0; i < AssingedEventList.size(); i++) {
				Response resp = GP.removeGroup(AssingedEventList.get(i));
				if (resp.statusCode() == 200) {
					System.out.println(
							"Event " + AssingedEventList.get(i) + " has been removed successfully for the user.");
				}
			}
		} 

	}

	/**
	 * Method to initialize Assert objects before each Test Method.
	 *
	 * @throws Exception throws all exception  
	 */	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception {
		loginPage = new LoginPage(getDriver(), getPlatformName());

		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
		loginPage.LoginWithEmail();
		homePage = new HomePage(getDriver(), getPlatformName());

		logAssert = new LogAssert();
	}

	@Test(enabled = true, priority = 1, testName = "Verify Live Events Page Elements", description = "To verify Live Events Page Loading and Elements displayed in the page")
	public void verifyEventsPageLoad() throws Exception {
		liveEventsPage = homePage.goToLiveEventsPage();
		logAssert.assertTrue(liveEventsPage.isElementDisplayed("Live events"),
				"\"Live events\" Title should be displayed");

		Response resp = GP.GetEventsByUser("UPCOMING");
		List<String> UpcomingEventList;

		if (resp.statusCode() == 200) {
			UpcomingEventList = resp.jsonPath().getList("value.\"topic.aiabase_name\"");
		} else {
			UpcomingEventList = Collections.emptyList();
		}

		if (UpcomingEventList.size() > 0) {
			logAssert.assertTrue(liveEventsPage.isElementDisplayed("Upcoming"),
					"\"Upcoming\" Section should be displayed");
		}
		
		Response respRecomEvents = GP.getRecommendedEvents();
		List<String> TopicList = respRecomEvents.jsonPath().getList("value.listid");
		if (TopicList.size()>0) {
			logAssert.assertTrue(liveEventsPage.isElementDisplayed("See all live events"),
					"\"See all live events\" Button should be displayed");			
		}		

		logAssert.assertTrue(liveEventsPage.isElementDisplayed("Add more interests"),
				"\"Add more interests\" Section should be displayed");
		logAssert.assertTrue(
				liveEventsPage.isElementDisplayed("See more live events based on the interests that you choose."),
				"\"See more live events based on the interests that you choose.\" text should be displayed");
		logAssert.assertTrue(liveEventsPage.isElementDisplayed("Interested in something else?"),
				"\"Interested in something else? section should be displayed");
		logAssert.assertTrue(liveEventsPage.isElementDisplayed("Drop us a message"),
				"\"Drop us a message\" link should be displayed");
		liveEventsPage.BackButton();
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 2, testName = "Verify Recommended Events in Live Event Page", description = "To verify Recommended events and click on the Event card.")
	public void verifyRecommendedEventCard() throws Exception {
		liveEventsPage = homePage.goToLiveEventsPage();
		GenericFunctions.scrollByCordinates(getDriver());
		
		Response resp = GP.getRecommendedEvents(); 
		List<String> EventList;
		
		if (resp.statusCode() == 200) {
			EventList = resp.jsonPath().getList("value.aiabase_Topic.aiabase_name");
		} else {
			EventList = Collections.emptyList();
		}	
		
		if (EventList.size() > 0) {

			for (int i = 0; i < EventList.size(); i++) {
				GenericFunctions.scroll(getDriver(), EventList.get(i), getPlatformName());
				logAssert.assertTrue(liveEventsPage.isElementDisplayed(EventList.get(i)), "Event Card details \""
						+ EventList.get(i) + "\" available in API response should be displayed");
			}
			EventDetailsPage EDP = liveEventsPage.goToRecommendedEventDetailsPage();
			
			logAssert.assertTrue(EDP.isElementDisplayed(EventList.get(0)),
					"Event Details page should be opened successfully when clicked from Recommended Event Card.");
			
			EDP.BackButton();			
		}		
		else {
			ExtentReportManager.getTest().fail("No Recommended Events available to test.");
		}
		
		liveEventsPage.BackButton();
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 3, testName = "Verify All Live Events", description = "To click on All Events link in Events page and check All Events Page Load.")
	public void verifyAllLiveEvents() throws Exception {
		liveEventsPage = homePage.goToLiveEventsPage();
		AllLiveEventsPage AllEvents = liveEventsPage.goToSeeAllEvents();
		logAssert.assertTrue(AllEvents.isElementDisplayed("All live events"), "\"All live events\" text is displayed");

		Response resp = GP.getAllTopics("LIVE_EVENT");
		List<String> EventList;

		if (resp.statusCode() == 200) {
			EventList = resp.jsonPath().getList("value.aiabase_name");
		} else {
			EventList = Collections.emptyList();
		}

		if (EventList.size() > 0) {

			for (int i = 0; i < EventList.size(); i++) {
				GenericFunctions.scroll(getDriver(), EventList.get(i), getPlatformName());
				logAssert.assertTrue(AllEvents.isElementDisplayed(EventList.get(i)), "Event Card details \""
						+ EventList.get(i) + "\" available in API response should be displayed");
			}
		}
		else {
			ExtentReportManager.getTest().fail("No Live Events available to test.");
		}

		AllEvents.clickOnBackButton();
		liveEventsPage.BackButton();
	}

	@Test(enabled = true, priority = 4, testName = "Verify Event Details Page layout for unassigned events", description = "Verify Event Details Page layout for unassigned events.")
	public void verifyEventDetailsPageUnAssigned() throws Exception {
		liveEventsPage = homePage.goToLiveEventsPage();
		AllLiveEventsPage AllEvents = liveEventsPage.goToSeeAllEvents();
		logAssert.assertTrue(AllEvents.isElementDisplayed("All live events"), "\"All live events\" text is displayed");

		boolean EventDetailsTested = false;

		Response resp = GP.getAllTopics("LIVE_EVENT");
		List<String> EventList;

		if (resp.statusCode() == 200) {
			EventList = resp.jsonPath().getList("value.aiabase_name");
		} else {
			EventList = Collections.emptyList();
		}

		if (EventList.size() > 0) {

			for (int i = 0; i < EventList.size(); i++) {
				if (EventDetailsTested) {
					break;
				}

				if (!AllEvents.isElementDisplayed(EventList.get(i))) {
					ExtentReportManager.getTest().log(Status.SKIP, "No Card for \"" + EventList.get(i)
							+ "\" is available in the page, skipping to the next event.");
					continue;
				}

				ExtentReportManager.getTest().log(Status.INFO, "Clicking on Event Card - \"" + EventList.get(i) + "\"");
				GenericFunctions.scroll(getDriver(), EventList.get(i), getPlatformName());
				GenericFunctions.clickElement(getDriver(),
						GenericFunctions.findElementByText(getDriver(), EventList.get(i), getPlatformName()));

				EventDetailsPage EDP = new EventDetailsPage(getDriver(), getPlatformName());

				if (EDP.isElementDisplayed(EventList.get(i))) {
					if (AllEvents.isElementDisplayed(EventList.get(i))) {
						logAssert.assertTrue(true,
								"\"" + EventList.get(i) + "\" screen should be opened successfully.");

						GenericFunctions.scroll(getDriver(), "LIVE FORMAT", getPlatformName());
						logAssert.assertTrue(EDP.isElementDisplayed("LIVE FORMAT"),
								"\"LIVE FORMAT\" Heading should be displayed");

						GenericFunctions.scroll(getDriver(), "DURATION", getPlatformName());
						logAssert.assertTrue(EDP.isElementDisplayed("DURATION"),
								"\"DURATION\" Heading should be displayed");

						GenericFunctions.scroll(getDriver(), "HOW TO PARTICIPATE", getPlatformName());
						logAssert.assertTrue(EDP.isElementDisplayed("HOW TO PARTICIPATE"),
								"\"HOW TO PARTICIPATE\" Heading should be displayed");

						GenericFunctions.scroll(getDriver(), "Meet your specialist", getPlatformName());
						logAssert.assertTrue(EDP.isElementDisplayed("Meet your specialist"),
								"\"Meet your specialist\" Heading should be displayed");

						GenericFunctions.scroll(getDriver(), "Choose from available schedules", getPlatformName());
						logAssert.assertTrue(EDP.isElementDisplayed("Choose from available schedules"),
								"\"Choose from available schedules\" Heading should be displayed");

						GenericFunctions.scroll(getDriver(), "Interested in something else?", getPlatformName());
						logAssert.assertTrue(EDP.isElementDisplayed("Interested in something else?"),
								"\"Choose from available schedules\" Title should be displayed");

						GenericFunctions.scroll(getDriver(), "Drop us a message", getPlatformName());
						logAssert.assertTrue(EDP.isElementDisplayed("Drop us a message"),
								"\"Drop us a message\" link should be displayed");

						EventDetailsTested = true;
						EDP.BackButton();
					} else {
						logAssert.assertTrue(false,
								"\"" + EventList.get(i) + "\" screen should be opened successfully.");
						ExtentReportManager.getTest().log(Status.INFO, "Skipping to the next live event.");
						getDriver().navigate().back();
						if (!AllEvents.isElementDisplayed("All live events")) {
							getDriver().navigate().back();
						}
					}

				} else {
					logAssert.assertTrue(false, "\"" + EventList.get(i) + "\" screen should be opened successfully.");
					ExtentReportManager.getTest().log(Status.INFO, "Skipping to the next live event.");
					getDriver().navigate().back();
					if (!AllEvents.isElementDisplayed("All live events")) {
						getDriver().navigate().back();
					}					
				}
			}
		} else {
			ExtentReportManager.getTest().fail("No Live Events available to test.");
		}

		AllEvents.clickOnBackButton();
		liveEventsPage.BackButton();
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, testName = "Verify Upcoming Live Events Card in Home Page before scheduling any live Event", description = "To verify Upcoming Live Events Card in Home Page before scheduling any live Event")
	public void verifyUpcomingEventCardHPBeforeEventScheduling() throws Exception {
		verifyUpcomingEventCardHP();
	}

	@Test(enabled = true, priority = 6, testName = "Verify Events Scheduling", description = "To verify Live Events scheduling and check if the scheduled event appears in upcoming event list")
	public void verifyEventScheduling() throws Exception {		
		Response respEventDtls;
		
		liveEventsPage = homePage.goToLiveEventsPage();
		AllLiveEventsPage AllEvents = liveEventsPage.goToSeeAllEvents();

		boolean EventDetailsTested = false;

		Response respAllEvents = GP.getAllTopics("LIVE_EVENT");
		String jsonAllEvents = respAllEvents.asString();
		List<String> EventList;

		Response respAssignedEventList = GP.getGroupByUserID("LIVE_EVENT");
		List<String> AssingedEventList = respAssignedEventList.jsonPath().getList("value.aiabase_Topic.aiabase_name");

		if (respAllEvents.statusCode() == 200) {
			EventList = respAllEvents.jsonPath().getList("value.aiabase_name");
		} else {
			EventList = Collections.emptyList();
		}

		if (EventList.size() > 0) {
			System.out.println(AssingedEventList.toString());
			List<String> unAssingedEventList = EventList.stream()
					.filter(o1 -> AssingedEventList.stream().noneMatch(o2 -> o2.equals(o1)))
					.collect(Collectors.toList());
			System.out.println(unAssingedEventList.toString());
			ExtentReportManager.getTest().log(Status.INFO,
					"There are " + unAssingedEventList.size() + " unassigned events available for the user.");
			if (unAssingedEventList.size() > 0) {
				for (int i = 0; i < unAssingedEventList.size(); i++) {
					if (EventDetailsTested) {
						break;
					}

					if (!AllEvents.isElementDisplayed(unAssingedEventList.get(i))) {
						ExtentReportManager.getTest().log(Status.SKIP, "No Card for \"" + unAssingedEventList.get(i)
								+ "\" is available in the page, skipping to the next event.");
						continue;
					}

					ExtentReportManager.getTest().log(Status.INFO,
							"Clicking on Event Card - \"" + unAssingedEventList.get(i) + "\"");
					GenericFunctions.scroll(getDriver(), unAssingedEventList.get(i), getPlatformName());
					GenericFunctions.clickElement(getDriver(), GenericFunctions.findElementByText(getDriver(),
							unAssingedEventList.get(i), getPlatformName()));

					EventDetailsPage EDP = new EventDetailsPage(getDriver(), getPlatformName());

					if (EDP.isElementDisplayed(unAssingedEventList.get(i))) {
						if (EDP.isElementDisplayed(unAssingedEventList.get(i))) {
							logAssert.assertTrue(true,
									"\"" + unAssingedEventList.get(i) + "\" screen should be opened successfully.");
																											
							List<String> selectedEvent = JsonPath.from(jsonAllEvents).getList("value.findAll {it.aiabase_name=='" + unAssingedEventList.get(i) + "' }.aiabase_groupmasterid"); 
														
							respEventDtls = GP.getEventByTopicID(selectedEvent.get(0));							
							//int memCountBefore = respEventDtls.jsonPath().getInt("value[0].membercount");
							//int totalCountBefore = respEventDtls.jsonPath().getInt("value[0].aiabase_maximumallowedcount");
							//ExtentReportManager.getTest().log(Status.INFO, "Total seats before event scheduling is " + (totalCountBefore-memCountBefore));
							
							EDP.ScheduleLiveEvent();

							EDP.ConfirmYourSeats();
							Thread.sleep(10000);
							respEventDtls = GP.getEventByTopicID(selectedEvent.get(0));							
							//int memCountAfter = respEventDtls.jsonPath().getInt("value[0].membercount");
							//int totalCountAfter = respEventDtls.jsonPath().getInt("value[0].aiabase_maximumallowedcount");
							//ExtentReportManager.getTest().log(Status.INFO, "Total seats after event scheduling is " + (totalCountAfter-memCountAfter));
							
							//logAssert.assertEquals(memCountAfter, memCountBefore+1, "Total available seats should be decremented by 1 after scheduling the event.");
							
							EventDetailsTested = true;
						} else {
							logAssert.assertTrue(false,
									"\"" + EventList.get(i) + "\" screen should be opened successfully.");
							ExtentReportManager.getTest().log(Status.INFO, "Skipping to the next live event.");
							getDriver().navigate().back();
							if (!AllEvents.isElementDisplayed("All live events")) {
								getDriver().navigate().back();
							}							
						}
					} else {
						logAssert.assertTrue(false,
								"\"" + EventList.get(i) + "\" screen should be opened successfully.");
						ExtentReportManager.getTest().log(Status.INFO, "Skipping to the next live event.");
						getDriver().navigate().back();
						if (!AllEvents.isElementDisplayed("All live events")) {
							getDriver().navigate().back();
						}						
					}
				}
			} else {
				ExtentReportManager.getTest().log(Status.FAIL, "There are no live events available for scheduling.");
			}
		} else {
			ExtentReportManager.getTest().fail("No Live Events available to test.");
		}
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 7, testName = "Verify Upcoming Event Card in Live Events Page", description = "To verify Upcoming Event Card in Live Events Page")
	public void verifyUpcomingEventCard() throws Exception {
		liveEventsPage = homePage.goToLiveEventsPage();

		logAssert.assertTrue(liveEventsPage.isElementDisplayed("See all live events"),
				"Live Events page should be displayed");

		Response resp = GP.GetEventsByUser("UPCOMING");
		List<String> UpcomingEventList;

		if (resp.statusCode() == 200) {
			UpcomingEventList = resp.jsonPath().getList("value.\"topic.aiabase_name\"");
		} else {
			UpcomingEventList = Collections.emptyList();
		}

		if (UpcomingEventList.size() > 0) {
			EventDetailsPage EDP = liveEventsPage.goToEventDetailsPage();

			logAssert.assertTrue(EDP.isElementDisplayed("Join online"), "Join online button should be displayed");
			logAssert.assertTrue(EDP.isElementDisplayed("Invite friends"), "Invite friends link should be displayed");

			EDP.inviteToEvent();
			logAssert.assertTrue(EDP.isElementDisplayed("Share"), "Share Menu Heading should be displayed");
			
			EDP.clickOnCopyButton();			

			GenericFunctions.scroll(getDriver(), "Similar live events", getPlatformName());
			logAssert.assertTrue(EDP.isElementDisplayed("Similar live events"),
					"Similar live events heading should be displayed");

			EDP.BackButton();
			liveEventsPage.BackButton();
		} else {
			ExtentReportManager.getTest().log(Status.FAIL, "No Upcoming live events available for the user.");
		}
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 8, testName = "Verify See All Upcoming Events Page", description = "To verify See all Upcoming Events page when clicked from Live Events Page")
	public void verifySeeAllUpcomingEvents() throws Exception {
		liveEventsPage = homePage.goToLiveEventsPage();
		logAssert.assertTrue(liveEventsPage.isElementDisplayed("See all live events"),
				"Live Events page should be displayed");
		Response resp = GP.GetEventsByUser("UPCOMING");
		List<String> UpcomingEventList;

		if (resp.statusCode() == 200) {
			UpcomingEventList = resp.jsonPath().getList("value.\"topic.aiabase_name\"");
		} else {
			UpcomingEventList = Collections.emptyList();
		}

		if (UpcomingEventList.size() > 0) {
			MyLiveEventsPage MLEP = liveEventsPage.goToMyLiveEventsPage();

			logAssert.assertTrue(MLEP.isElementDisplayed("Your live events"),
					"Your live events heading should be displayed");
			logAssert.assertTrue(MLEP.isElementDisplayed("Upcoming"), "Upcoming tab should be displayed");

			for (int i = 0; i < UpcomingEventList.size(); i++) {
				GenericFunctions.scroll(getDriver(), UpcomingEventList.get(i), getPlatformName());
				logAssert.assertTrue(MLEP.isElementDisplayed(UpcomingEventList.get(i)), UpcomingEventList.get(i)
						+ " Event Card details retrieved from api response should be displayed");
			}

			logAssert.assertTrue(MLEP.isElementDisplayed("Past"), "Past tab should be displayed");

			Response respPastEvents = GP.GetEventsByUser("PAST");
			List<String> PastEventList;

			if (resp.statusCode() == 200) {
				PastEventList = respPastEvents.jsonPath().getList("value.\"topic.aiabase_name\"");
			} else {
				PastEventList = Collections.emptyList();
			}

			MLEP.PastTabPage();

			for (int i = 0; i < PastEventList.size(); i++) {
				GenericFunctions.scroll(getDriver(), PastEventList.get(i), getPlatformName());
				logAssert.assertTrue(MLEP.isElementDisplayed(PastEventList.get(i)),
						PastEventList.get(i) + " Event Card details retrieved from API response should be displayed");
			}

			MLEP.BackButton();
			liveEventsPage.BackButton();
		} else {
			ExtentReportManager.getTest().log(Status.FAIL, "No Upcoming live events available for the user.");
		}
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 9, testName = "Verify Upcoming Live Events Card in Home Page", description = "To verify upcoming Live Event Card in Home Page")
	public void verifyUpcomingEventCardHP() throws Exception {
		Response resp = GP.GetEventsByUser("UPCOMING");
		List<String> UpcomingEventList;

		if (resp.statusCode() == 200) {
			UpcomingEventList = resp.jsonPath().getList("value.\"topic.aiabase_name\"");
		} else {
			UpcomingEventList = Collections.emptyList();
		}

		if (UpcomingEventList.size() > 0) {
			GenericFunctions.scrollByCordinatesUp(getDriver());
			GenericFunctions.scrollByCordinates(getDriver());
			String eventTitle = UpcomingEventList.get(0);
			EventDetailsPage EDP = homePage.goToEventDetailsPage();

			ExtentReportManager.getTest().log(Status.INFO, "Click on upcoming event card from Home page.");

			logAssert.assertTrue(EDP.isElementDisplayed(eventTitle),"Event Card Title in home page is not matching with Details page.");

			EDP.BackButton();
			
			MyLiveEventsPage MLEP = homePage.goToMyEventsPage();
			
			logAssert.assertTrue(MLEP.isElementDisplayed("Your live events"),
					"Your live events heading should be displayed");
			logAssert.assertTrue(MLEP.isElementDisplayed("Upcoming"), "Upcoming tab should be displayed");

			for (int i = 0; i < UpcomingEventList.size(); i++) {
				GenericFunctions.scroll(getDriver(), UpcomingEventList.get(i), getPlatformName());
				logAssert.assertTrue(MLEP.isElementDisplayed(UpcomingEventList.get(i)), UpcomingEventList.get(i)
						+ " Event Card details retrieved from api response should be displayed");
			}

			logAssert.assertTrue(MLEP.isElementDisplayed("Past"), "Past tab should be displayed");

			Response respPastEvents = GP.GetEventsByUser("PAST");
			List<String> PastEventList;

			if (resp.statusCode() == 200) {
				PastEventList = respPastEvents.jsonPath().getList("value.\"topic.aiabase_name\"");
			} else {
				PastEventList = Collections.emptyList();
			}

			MLEP.PastTabPage();

			for (int i = 0; i < PastEventList.size(); i++) {
				GenericFunctions.scroll(getDriver(), PastEventList.get(i), getPlatformName());
				logAssert.assertTrue(MLEP.isElementDisplayed(PastEventList.get(i)),
						PastEventList.get(i) + " Event Card details retrieved from API response should be displayed");
			}

			MLEP.BackButton();			
			
		} else {
			ExtentReportManager.getTest().log(Status.INFO, "No Upcoming live events available for the user.");
			logAssert.assertTrue(homePage.isElementDisplayed("You’ve not joined any live events yet."),
					"Live events section in Homepage should notify that there are no upcoming events for the user.");
			logAssert.assertTrue(homePage.isElementDisplayed("Discover live events"),
					"Discover live events button should be displayed when there are no upcoming events for the user.");
			LiveEventsPage LEP = homePage.discoverLiveEventsPage();
			logAssert.assertTrue(LEP.isElementDisplayed("See all live events"),
					"Live Events page should be displayed");
		}
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 10, testName = "Verify Interests in Live Events Page", description = "To verify See all Upcoming Events page when clicked from Live Events Page")
	public void verifyAllInterests() throws Exception {
		liveEventsPage = homePage.goToLiveEventsPage();

		String userProfileID = UC.getProfiles().jsonPath().getString("contactid");
		System.out.println(userProfileID);

		Response resp = UC.getInterestsByProfile(userProfileID);
		List<String> lstInterests;

		if (resp.statusCode() == 200) {
			lstInterests = resp.jsonPath().getList("\"int.aiabase_name\"");
		} else {
			lstInterests = Collections.emptyList();
		}

		System.out.println(lstInterests.size());
		if (lstInterests.size() > 0) {

			GenericFunctions.scroll(getDriver(), "Add more interests", getPlatformName());

			ExtentReportManager.getTest().log(Status.INFO,
					"Checking if all the profile \"Interests\" are displayed successfully in the page.");

			for (int i = 0; i < lstInterests.size(); i++) {
				System.out.println(lstInterests.get(i));
				// GenericFunctions.scroll(getDriver(), lstInterests.get(i), getPlatformName());
				logAssert.assertTrue(liveEventsPage.isElementDisplayed(lstInterests.get(i)),
						"\"" + lstInterests.get(i) + "\" Interest available in api response should be displayed");
			}
		}

		InterestsPage IP = liveEventsPage.seeAllInterests();
		logAssert.assertTrue(IP.isElementDisplayed("Your preferred interests"),
				"Interest Page should be displayed successfully");

		IP.CloseButton();
		liveEventsPage.BackButton();
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 11, testName = "Verify Drop us a message link in Live Events Page", description = "To verify Drop us a message link in Live Events Page")
	public void verifyContactUsPageFromLiveEvents() throws Exception {
		GenericFunctions.scrollByCordinatesUp(getDriver());
		liveEventsPage = homePage.goToLiveEventsPage();
		ContactUsPage CP = liveEventsPage.clickDropMessage();
		logAssert.assertTrue(CP.isElementDisplayed("Contact us"), "Contact us page should be displayed.");
		CP.BackButton();
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 12, testName = "Verify Drop us a message link in Event Details Page", description = "To verify Drop us a message link in Event Details Page")
	public void verifyContactUsPageFromEventDtlsPage() throws Exception {
		boolean AssingedEventTested = false;
		boolean unAssignedEventTested = false;

		GenericFunctions.scrollByCordinatesUp(getDriver());
		liveEventsPage = homePage.goToLiveEventsPage();
		AllLiveEventsPage AllEvents = liveEventsPage.goToSeeAllEvents();

		Response resp = GP.getAllTopics("LIVE_EVENT");
		List<String> EventList;

		Response respAssignedEventList = GP.getGroupByUserID("LIVE_EVENT");
		List<String> AssingedEventList = respAssignedEventList.jsonPath().getList("value.aiabase_Topic.aiabase_name");
		
		if (AssingedEventList.size() == 0) {
			AssingedEventTested = true;
		}

		if (resp.statusCode() == 200) {
			EventList = resp.jsonPath().getList("value.aiabase_name");
		} else {
			EventList = Collections.emptyList();
		}

		if (EventList.size() > 0) {
			for (int i = 0; i < EventList.size(); i++) {
				if (unAssignedEventTested && AssingedEventTested) {
					break;
				}
				GenericFunctions.scroll(getDriver(), EventList.get(i), getPlatformName());
				ExtentReportManager.getTest().log(Status.INFO, "Clicking on Event Card - \"" + EventList.get(i) + "\"");
				if (AllEvents.isElementDisplayed(EventList.get(i))) {
					GenericFunctions.clickElement(getDriver(),
							GenericFunctions.findElementByText(getDriver(), EventList.get(i), getPlatformName()));
					if (AllEvents.isElementDisplayed(EventList.get(i))) {
						logAssert.assertTrue(AllEvents.isElementDisplayed(EventList.get(i)),
								"\"" + EventList.get(i) + "\" screen should be opened successfully.");
						EventDetailsPage EDP = new EventDetailsPage(getDriver(), getPlatformName());

						if (AssingedEventList.contains(EventList.get(i))) {
							if (!AssingedEventTested) {
								logAssert.assertFalse(EDP.isElementDisplayed("Drop us a message"),
										"\"Drop us a message\" link should be displayed");
								AssingedEventTested = true;
							}
						} else {
							if (!unAssignedEventTested) {
								logAssert.assertTrue(EDP.isElementDisplayed("Drop us a message"),
										"\"Drop us a message\" link should be displayed");
								ContactUsPage CP = EDP.clickDropMessage();
								logAssert.assertTrue(CP.isElementDisplayed("Contact us"),
										"\"Contact us\" page should be displayed");
								CP.BackButton();
								unAssignedEventTested = true;
							}
						}
						// EDP.BackButton();
						getDriver().navigate().back();
						if (!AllEvents.isElementDisplayed("All live events")) {
							getDriver().navigate().back();
						}						
					} else {
						logAssert.assertTrue(false,
								"\"" + EventList.get(i) + "\" screen should be opened successfully.");
						getDriver().navigate().back();
						if (!AllEvents.isElementDisplayed("All live events")) {
							getDriver().navigate().back();
						}						
					}
				} else {
					logAssert.assertTrue(false,
							"\"" + EventList.get(i) + "\" card is not displayed in  All Events Page.");
				}
			}
		} else {
			ExtentReportManager.getTest().fail("There are no live events available to Test.");
		}
		logAssert.assertAll();
		// EventDetailsPage EDP = AllEvents.clickOnLiveEventCard();
	}
}
