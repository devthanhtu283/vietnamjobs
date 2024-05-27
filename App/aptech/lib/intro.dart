import 'package:aptech/seeker/dashboard.dart';
import 'package:aptech/login.dart';
import 'package:aptech/models/on_boarding.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:salomon_bottom_bar/salomon_bottom_bar.dart';

class IntroPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return IntroPageState();
  }
}

class IntroPageState extends State<IntroPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: OnboardingPagePresenter(pages: [
        OnBoardingPageModel(
          title: 'Worthy Jobs',
          description: 'Fast advancement, attractive job, moderate income',
          imageUrl: 'https://lh3.googleusercontent.com/drive-viewer/AKGpihbEXRdE3Qsd_OgM7WKnsT8jCJwdOPw7wwWPnuDzfAnrouMIqxzitr7-vxuKpTvWdQF3ODLsqFSQFX0mLQpqaDTp2nw-s4ZA_jY=w1920-h956',
          bgColor: const Color(0xFF244256),
        ),
        OnBoardingPageModel(
          title: 'Personalize the experience',
          description: 'Using AI technology to predict and personalize jobs',
          imageUrl: 'https://lh3.googleusercontent.com/u/0/drive-viewer/AKGpihbCxI7HfEtjPRWtnpwAi_2vMyxDSx4MU5kKWL9aND2VrDT-PO2MnuvI5YS26lyd6PDg2vxgsn0katX46SpBnbsHcZ86Qf8DkHg=w1876-h956',
          bgColor: const Color(0xFF244256),
        ),
        OnBoardingPageModel(
          title: 'Accompany you',
          description:
              'Search, connect, build success',
          imageUrl: 'https://lh3.googleusercontent.com/drive-viewer/AKGpihZjy6VJTKLR9PgzCJktGU2hXwb_z_ZR_O0-2r8VVkxOY-ViLvXXqqykk1_cwlYInN7UtwoSRzW1fl9uX7cFctr4nacrJPfUZHk=w1920-h956',
          bgColor: const Color(0xFF244256),
        ),
        OnBoardingPageModel(
          title: 'Recruiters are everywhere',
          description: 'More than 1000+ employers from everywhere',
          imageUrl: 'https://lh3.googleusercontent.com/u/0/drive-viewer/AKGpihZrhwLA5SRzIsjX9NAWeTu12fAEvU02ClFqdOxUf7hg_-ghjzLDKHHdO-QBpKmPhAd4GFenPZ02ye7UTmoAJF56nhm7NgEIsGM=w1876-h956',
          bgColor: Color(0xFF244256),
        ),
      ]),
    );
  }
}

class OnboardingPagePresenter extends StatefulWidget {
  final List<OnBoardingPageModel> pages;
  final VoidCallback? onSkip;
  final VoidCallback? onFinish;

  const OnboardingPagePresenter(
      {Key? key, required this.pages, this.onSkip, this.onFinish})
      : super(key: key);

  @override
  State<OnboardingPagePresenter> createState() => _OnboardingPageState();
}

class _OnboardingPageState extends State<OnboardingPagePresenter> {
  // Store the currently visible page
  int _currentPage = 0;

  // Define a controller for the pageview
  final PageController _pageController = PageController(initialPage: 0);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: AnimatedContainer(
        duration: const Duration(milliseconds: 250),
        color: widget.pages[_currentPage].bgColor,
        child: SafeArea(
          child: Column(
            children: [
              Expanded(
                // Pageview to render each page
                child: PageView.builder(
                  controller: _pageController,
                  itemCount: widget.pages.length,
                  onPageChanged: (idx) {
                    // Change current page when pageview changes
                    setState(() {
                      _currentPage = idx;
                    });
                  },
                  itemBuilder: (context, idx) {
                    final item = widget.pages[idx];
                    return Column(
                      children: [
                        Expanded(
                          flex: 3,
                          child: Padding(
                            padding: const EdgeInsets.all(32.0),
                            child: Image.network(
                              item.imageUrl as String,
                            ),
                          ),
                        ),
                        Expanded(
                            flex: 1,
                            child: Column(children: [
                              Padding(
                                padding: const EdgeInsets.all(16.0),
                                child: Text("${item.title}",
                                    style: Theme.of(context)
                                        .textTheme
                                        .headline6
                                        ?.copyWith(
                                          fontWeight: FontWeight.bold,
                                          color: Colors.white,
                                        )),
                              ),
                              Container(
                                constraints:
                                    const BoxConstraints(maxWidth: 280),
                                padding: const EdgeInsets.symmetric(
                                    horizontal: 24.0, vertical: 8.0),
                                child: Text("${item.description}",
                                    textAlign: TextAlign.center,
                                    style: Theme.of(context)
                                        .textTheme
                                        .bodyText2
                                        ?.copyWith(
                                          color: Colors.white,
                                      fontSize: 18
                                        )),
                              )
                            ]))
                      ],
                    );
                  },
                ),
              ),

              // Current page indicator
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: widget.pages
                    .map((item) => AnimatedContainer(
                          duration: const Duration(milliseconds: 250),
                          width: _currentPage == widget.pages.indexOf(item)
                              ? 30
                              : 8,
                          height: 8,
                          margin: const EdgeInsets.all(2.0),
                          decoration: BoxDecoration(
                              color: Colors.white,
                              borderRadius: BorderRadius.circular(10.0)),
                        ))
                    .toList(),
              ),

              // Bottom buttons
              SizedBox(
                height: 100,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    TextButton(
                        style: TextButton.styleFrom(
                            visualDensity: VisualDensity.comfortable,
                            foregroundColor: Colors.white,
                            textStyle: const TextStyle(
                                fontSize: 16, fontWeight: FontWeight.bold)),
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(builder: (context) => LoginPage(),),
                          );
                        },
                        child: const Text("Skip")),
                    TextButton(
                      style: TextButton.styleFrom(
                          visualDensity: VisualDensity.comfortable,
                          foregroundColor: Colors.white,
                          textStyle: const TextStyle(
                              fontSize: 16, fontWeight: FontWeight.bold)),
                      onPressed: () {
                        if (_currentPage == widget.pages.length - 1) {
                          Navigator.push(
                            context,
                            MaterialPageRoute(builder: (context) => LoginPage(),),
                          );
                        } else {
                          _pageController.animateToPage(_currentPage + 1,
                              curve: Curves.easeInOutCubic,
                              duration: const Duration(milliseconds: 250));
                        }
                      },
                      child: Row(
                        children: [
                          Text(
                            _currentPage == widget.pages.length - 1
                                ? "Complete"
                                : "Next",
                          ),
                          const SizedBox(width: 8),
                          Icon(_currentPage == widget.pages.length - 1
                              ? Icons.done
                              : Icons.arrow_forward),
                        ],
                      ),
                    ),
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}

void call() {
  print("aaaa");
}
