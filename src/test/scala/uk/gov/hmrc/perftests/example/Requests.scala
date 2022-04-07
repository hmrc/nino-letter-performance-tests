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





























}
