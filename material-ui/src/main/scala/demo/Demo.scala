package demo

import demo.album.Album
import demo.button.{ButtonTest, SelectDemo, StyledButtonDemo, StyledButtonHooksDemo}
import demo.components.AppTheme
import demo.customization.{DarkTheme, Palette}
import demo.dashboard.Dashboard
import demo.login.Login
import demo.signin.SignIn
import org.scalajs.dom
import slinky.core.FunctionalComponent
import slinky.core.facade.Fragment
import slinky.web.ReactDOM
import typings.materialUiCore.components.{List, ListItem, ListItemIcon, ListItemText, ListSubheader, Typography}
import typings.materialUiCore.createMuiThemeMod.{Theme, ThemeOptions}
import typings.materialUiCore.createTypographyMod.TypographyOptions
import typings.materialUiCore.stylesMod.createMuiTheme
import typings.materialUiCore.typographyTypographyMod.Style
import typings.materialUiIcons.{components => Icon}
import typings.materialUiStyles.components.ThemeProvider
import typings.reactRouter.mod.RouteProps
import typings.reactRouterDom.components.{BrowserRouter, Link, Route}

object Demo {

  val theme: Theme = createMuiTheme(
    ThemeOptions()
      .setTypography(
        TypographyOptions().setUseNextVariants(true)
      ) // https://v3.material-ui.com/style/typography/#migration-to-typography-v2
  )

  type Props = Unit

  /* the production build is deployed at github pages under /material-ui , while dev build is server from root of webpack-dev-server */
  val basename = if (scala.scalajs.runtime.linkingInfo.productionMode) "/SlinkyDemos/material-ui/" else ""

  val Main: FunctionalComponent[Props] = FunctionalComponent[Props] {
    case () =>
      ThemeProvider(theme)(
        BrowserRouter.basename(basename)(
          Route(
            RouteProps()
              .setExact(true)
              .setPath("/")
              .setRender(_ =>
                List(
                  ListSubheader.inset(true)(""),
                  Link[String](to = "/dashboard")(
                    ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Dashboard"))
                  ),
                  Link[String](to = "/album")(
                    ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Album"))
                  ),
                  Link[String](to = "/signin")(
                    ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Sign In"))
                  ),
                  Link[String](to = "/login")(
                    ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Login"))
                  ),
                  Link[String](to = "/button")(
                    ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Buttons"))
                  ),
                  Link[String](to = "/select")(
                    ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Select"))
                  ),
                  Link[String](to = "/customization")(
                    ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Customization"))
                  )
                )
              )
          ),
          Route(
            RouteProps()
              .setPath("/dashboard")
              .setRender(_ =>
                Fragment(
                  AppTheme(
                    title = "Dashboard page layout example - Material-UI",
                    description = "An example layout for creating an albumn.",
                    hideCredit = true
                  )(Dashboard())
                )
              )
          ),
          Route(
            RouteProps()
              .setPath("/album")
              .setRender(_ =>
                Fragment(
                  AppTheme(
                    title = "Album page layout - Material-UI",
                    description = "An example layout for creating an album or gallery."
                  )(Album())
                )
              )
          ),
          Route(
            RouteProps()
              .setPath("/signin")
              .setRender(_ =>
                Fragment(
                  AppTheme(
                    title = "Sign-in page layout example - Material-UI",
                    description = "An example layout for creating a sign-in page."
                  )(SignIn())
                )
              )
          ),
          Route(
            RouteProps()
              .setPath("/login")
              .setRender(_ => Login())
          ),
          Route(
            RouteProps()
              .setPath("/button")
              .setRender(_ =>
                Fragment(
                  Typography.variant(Style.h4).gutterBottom(true).component("h2")("Buttons"),
                  ButtonTest("dear user"),
                  StyledButtonDemo(),
                  StyledButtonHooksDemo()
                )
              )
          ),
          Route(
            RouteProps()
              .setPath("/select")
              .setRender(_ =>
                Fragment(
                  Typography.variant(Style.h4).gutterBottom(true).component("h2")("Select"),
                  SelectDemo(scala.List("one", "two", "three"))
                )
              )
          ),
          Route(
            RouteProps()
              .setPath("/customization")
              .setRender(_ =>
                Fragment(
                  Typography.variant(Style.h4).gutterBottom(true).component("h2")("Customization"),
                  DarkTheme(),
                  Palette()
                )
              )
          )
        )
      )
  }

  def main(argv: Array[String]): Unit = {
    println("starting")
    ReactDOM.render(
      Main(()),
      dom.document.getElementById("container")
    )
  }
}
