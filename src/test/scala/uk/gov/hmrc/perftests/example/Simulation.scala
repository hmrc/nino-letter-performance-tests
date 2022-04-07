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

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.example.Requests._

class Simulation extends PerformanceTestRunner {

  setup("home-page", "Home Page") withRequests navigateToHomePage

  setup("what-is-your-name-page", "Get/Post what is your name") withRequests (
    getWhatIsYourNamePage,
    postWhatIsYourNamePage
    )

  setup("previous-name-page", "Get/Post previous name page") withRequests (
    getPreviousNamePage,
    postPreviousNamePage
    )

  setup("date-of-birth-page", "Get/Post date of birth page") withRequests (
    getDOBPage,
    postDOBPage
    )

  setup("current-address-uk-page", "Get/Post current address uk page") withRequests (
    getCurrentAddressUKPage,
    postCurrentAddressUKPage
    )



  runSimulation()
}
