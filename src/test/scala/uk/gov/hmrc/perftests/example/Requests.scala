/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.example

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object Requests extends ServicesConfiguration {

  val baseUrl: String = baseUrlFor("national-insurance-number-letter-spike-frontend")

  val route: String   = "/get-your-national-insurance-number-by-post"

  val navigateToHomePage: HttpRequestBuilder =
    http("Navigate to Start Page")
      .get(s"$baseUrl$route/")
      .check(status.is(200))


  val getWhatIsYourNamePage: HttpRequestBuilder =
      http("Get What Is Your Name Page")
        .get(s"$baseUrl$route/what-is-your-name": String)
        .check(status.is(200))
        .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))


  val postWhatIsYourNamePage: HttpRequestBuilder =
    http("Post What Is Your Name")
      .post(s"$baseUrl$route/what-is-your-name": String)
      .formParam("firstName", "test")
      .formParam("lastName", "test")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/do-you-have-a-previous-name").saveAs("previousNamePage"))

  val getPreviousNamePage: HttpRequestBuilder =
    http("Get Previous Name Page")
      .get(s"$baseUrl$${previousNamePage}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postPreviousNamePage: HttpRequestBuilder =
    http("Post Previous Name Page")
      .post(s"$baseUrl$${previousNamePage}": String)
      .formParam("value", "false")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/what-is-your-date-of-birth").saveAs("dateOfBirthPage"))

  val getDOBPage: HttpRequestBuilder =
    http("Get Date Of Birth Page")
      .get(s"$baseUrl$${dateOfBirthPage}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postDOBPage: HttpRequestBuilder =
    http("Post Date Of Birth Page")
      .post(s"$baseUrl$${dateOfBirthPage}": String)
      .formParam("value.day", "06")
      .formParam("value.month", "04")
      .formParam("value.year", "2022")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/is-your-current-address-in-uk").saveAs("CurrentAddressUK"))

  val getCurrentAddressUKPage: HttpRequestBuilder =
    http("Get Current Address UK Page")
      .get(s"$baseUrl$${CurrentAddressUK}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postCurrentAddressUKPage: HttpRequestBuilder =
    http("Post Current Address UK Page")
      .post(s"$baseUrl$${CurrentAddressUK}": String)
      .formParam("value", "true")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/what-is-your-current-uk-address").saveAs("CurrentAddress"))

  val getCurrentAddressPage: HttpRequestBuilder =
    http("Get Current Address UK Page")
      .get(s"$baseUrl$${CurrentAddress}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postCurrentAddressPage: HttpRequestBuilder =
    http("Post Current Address UK Page")
      .post(s"$baseUrl$${CurrentAddress}": String)
      .formParam("addressLine1", "test")
      .formParam("postcode", "NE98 1ZZ")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/do-you-have-any-previous-addresses").saveAs("PreviousAddress"))

  val getPreviousAddressPage: HttpRequestBuilder =
    http("Get Previous Address Page")
      .get(s"$baseUrl$${PreviousAddress}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postPreviousAddressPage: HttpRequestBuilder =
    http("Post Previous Address Page")
      .post(s"$baseUrl$${PreviousAddress}": String)
      .formParam("value", "false")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/are-you-returning-from-living-abroad").saveAs("LivingAbroad"))

  val getLivingAbroadPage: HttpRequestBuilder =
    http("Get Living Abroad Page")
      .get(s"$baseUrl$${LivingAbroad}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLivingAbroadPage: HttpRequestBuilder =
    http("Post Living Abroad Page")
      .post(s"$baseUrl$${LivingAbroad}": String)
      .formParam("value", "false")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/what-is-your-telephone-number").saveAs("TelephoneNumber"))

  val getTelephoneNumberPage: HttpRequestBuilder =
    http("Get Telephone Number Page")
      .get(s"$baseUrl$${TelephoneNumber}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postTelephoneNumberPage: HttpRequestBuilder =
    http("Post Telephone Number Page")
      .post(s"$baseUrl$${TelephoneNumber}": String)
      .formParam("value", "62442")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/do-you-know-your-national-insurance-number").saveAs("Nino"))

  val getNinoPage: HttpRequestBuilder =
    http("Get Nino Page")
      .get(s"$baseUrl$${Nino}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postNinoPage: HttpRequestBuilder =
    http("Post Nino Page")
      .post(s"$baseUrl$${Nino}": String)
      .formParam("value", "false")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/are-you-married").saveAs("Married"))

  val getMarriedPage: HttpRequestBuilder =
    http("Get Telephone Number Page")
      .get(s"$baseUrl$${Married}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postMarriedPage: HttpRequestBuilder =
    http("Post Married Page")
      .post(s"$baseUrl$${Married}": String)
      .formParam("value", "false")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/are-you-in-a-civil-partnership").saveAs("CivilPartnership"))

  val getCivilPartnershipPage: HttpRequestBuilder =
    http("Get Civil Partnership Page")
      .get(s"$baseUrl$${CivilPartnership}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postCivilPartnershipPage: HttpRequestBuilder =
    http("Post Civil Partnership Page")
      .post(s"$baseUrl$${CivilPartnership}": String)
      .formParam("value", "false")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/have-you-been-in-a-previous-marriage-or-civil-partnership").saveAs("MarriageCivilPartnership"))

  val getMarriageCivilPartnershipPage: HttpRequestBuilder =
    http("Get Previous Marriage Or CivilPartnership Page")
      .get(s"$baseUrl$${MarriageCivilPartnership}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postMarriageCivilPartnershipPage: HttpRequestBuilder =
    http("Post Previous Marriage Or CivilPartnership Page")
      .post(s"$baseUrl$${MarriageCivilPartnership}": String)
      .formParam("value", "false")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/have-you-ever-claimed-child-benefit").saveAs("ChildBenefit"))

  val getChildBenefitPage: HttpRequestBuilder =
    http("Get ChildBenefit Page")
      .get(s"$baseUrl$${ChildBenefit}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postChildBenefitPage: HttpRequestBuilder =
    http("Post ChildBenefit Page")
      .post(s"$baseUrl$${ChildBenefit}": String)
      .formParam("value", "false")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/have-you-ever-received-other-uk-benefits").saveAs("OtherBenefits"))

  val getOtherBenefitsPage: HttpRequestBuilder =
    http("Get Other Benefits Page")
      .get(s"$baseUrl$${OtherBenefits}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postOtherBenefitsPage: HttpRequestBuilder =
    http("Post Other Benefits Page")
      .post(s"$baseUrl$${OtherBenefits}": String)
      .formParam("value", "false")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/have-you-ever-worked-in-uk").saveAs("WorkedInUK"))

  val getWorkedInUKPage: HttpRequestBuilder =
    http("Get Worked In UK Page")
      .get(s"$baseUrl$${WorkedInUK}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postWorkedInUKPage: HttpRequestBuilder =
    http("Post Worked In UK Page")
      .post(s"$baseUrl$${WorkedInUK}": String)
      .formParam("value", "false")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/do-you-have-an-identity-document").saveAs("Doc"))

  val getDocPage: HttpRequestBuilder =
    http("Get Doc Page")
      .get(s"$baseUrl$${Doc}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postDocPage: HttpRequestBuilder =
    http("Post Worked In UK Page")
      .post(s"$baseUrl$${Doc}": String)
      .formParam("value", "true")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/which-identity-document-do-you-have").saveAs("WhichDoc"))

  val getWhichDocPage: HttpRequestBuilder =
    http("Get Which Doc Page")
      .get(s"$baseUrl$${WhichDoc}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postWhichDocPage: HttpRequestBuilder =
    http("Post Which Doc Page")
      .post(s"$baseUrl$${WhichDoc}": String)
      .formParam("value", "birth-certificate")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is("/get-your-national-insurance-number-by-post/check-your-answers").saveAs("CheckAnswers"))

  val getCheckAnswersPage: HttpRequestBuilder =
    http("Get Check Answers Page")
      .get(s"$baseUrl$${CheckAnswers}": String)
      .check(status.is(200))

  val getNextStepPage: HttpRequestBuilder =
    http("Get Check Answers Page")
      .get(s"$baseUrl$route/next-steps": String)
      .check(status.is(200))

  val getPrintFormPage: HttpRequestBuilder =
    http("Get Print Form Page")
      .get(s"$baseUrl$route/print-form": String)
      .check(status.is(200))

}
